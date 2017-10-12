package uy.edu.ort.context;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DBConfig {

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
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl(this.jdbcUrl);
            dataSource.setUser(this.dbUser);
            dataSource.setPassword(this.dbPassword);
            dataSource.setMaxPoolSize(10);
            dataSource.setMinPoolSize(5);
            dataSource.setPreferredTestQuery("/* ping */ SELECT 1");

        } catch (PropertyVetoException e) {
            throw new RuntimeException("Error initializing db pool");
        }

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean =
                new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());

        sessionFactoryBean.setPackagesToScan("uy.edu.ort.model");

        Properties properties = new Properties();
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", this.hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", this.hibernateHbm2DdlAuto);
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject());

        return hibernateTemplate;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }


}
