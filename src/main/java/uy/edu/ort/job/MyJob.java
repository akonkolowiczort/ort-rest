package uy.edu.ort.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uy.edu.ort.dto.PersonaDTO;
import uy.edu.ort.service.PersonaService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MyJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyJob.class);

    private AtomicLong count = new AtomicLong();

    @Autowired
    private MessageChannel processChannel;

    @Autowired
    private PersonaService personaService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void jobMethod() {
        AtomicLong messageCount = new AtomicLong();
        LOGGER.info("running job ==> count: {} ", count.incrementAndGet());
        List<PersonaDTO> personas = personaService.obtenerPersonas();
        personas.forEach(persona -> {
            Message<PersonaDTO> message = MessageBuilder.withPayload(persona)
                    .setHeader("messageCount", messageCount.incrementAndGet()).build();
            processChannel.send(message);
        });

        LOGGER.info("Job count:{} finished. ", count.get());
    }
}
