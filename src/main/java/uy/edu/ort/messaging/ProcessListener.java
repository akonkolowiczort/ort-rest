package uy.edu.ort.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import uy.edu.ort.dto.PersonaDTO;

@Component
public class ProcessListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessListener.class);

    public void processMessage(Message<PersonaDTO> message) {
        PersonaDTO persona = message.getPayload();
        long messageCount = message.getHeaders().get("messageCount", Long.class);
        //procesar usuario
        LOGGER.info("mensaje numero {} persona procesada {}", messageCount, persona);
    }
}
