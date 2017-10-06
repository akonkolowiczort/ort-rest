package uy.edu.ort.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uy.edu.ort.service.PersonaServiceImpl;

@Component
public class MyJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaServiceImpl.class);

    private long count = 0;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void jobMethod() {
        count = count + 1;
        LOGGER.info("running job ==> count: {} ", count);
    }
}
