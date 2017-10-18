package uy.edu.ort.context;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:application-context-messaging.xml")
public class MessagingConfig {

    @Value("${queue.connection.expiry-timeout}")
    private long expiryTimeout;

    @Value("${queue.broker.url}")
    private String brokerURL;

    @Bean
    public PooledConnectionFactory connectionFactory() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(this.directConnectionFactory());
        pooledConnectionFactory.setExpiryTimeout(expiryTimeout);
        return pooledConnectionFactory;
    }
    @Bean
    public ActiveMQConnectionFactory directConnectionFactory() {
        ActiveMQConnectionFactory directConnectionFactory = new ActiveMQConnectionFactory();
        directConnectionFactory.setBrokerURL(brokerURL);
        return directConnectionFactory;
    }



}

