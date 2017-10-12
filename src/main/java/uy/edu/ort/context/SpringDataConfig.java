package uy.edu.ort.context;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.beans.PropertyVetoException;
import java.util.Properties;


/**
 * If you want to use spring-data Configuration,
 * you need to uncomment this annotations below and comment out [uy.edu.ort.context.DBConfig]'s annotations.
 */
//@Configuration
//@EnableJpaRepositories(basePackages = "uy.edu.ort.dao")
//@EnableTransactionManagement
public class SpringDataConfig {

    @Value("${db.jdbc.url}")
    private String jdbcUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.hibernate.show.sql}")
    private boolean hibernateShowSql;

    @Value("${db.hibernate.hbm2ddl.auto}")
    private String hibernateHbm2DdlAuto;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("uy.edu.ort.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", String.valueOf(hibernateShowSql));
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
        properties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(dbUser);
            dataSource.setPassword(dbPassword);
            dataSource.setMaxPoolSize(10);
            dataSource.setMinPoolSize(5);
            dataSource.setPreferredTestQuery("/* ping */ SELECT 1");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Error initializing db pool");
        }
        return dataSource;
    }
}
