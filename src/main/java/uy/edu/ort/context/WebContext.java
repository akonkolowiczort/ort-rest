package uy.edu.ort.context;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@Import({CacheContext.class, DBConfig.class, MessagingConfig.class})
@ComponentScan("uy.edu.ort")
public class WebContext {

    @Bean
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        ClassPathResource fileLocation = new ClassPathResource("application.properties"); // en carperta resources
        propertyPlaceholderConfigurer.setLocation(fileLocation);
        return propertyPlaceholderConfigurer;
    }

}
