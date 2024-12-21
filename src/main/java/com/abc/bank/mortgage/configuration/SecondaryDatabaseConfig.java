package com.abc.bank.mortgage.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.abc.bank.mortgage.bookshop.repository", // Adjust the package to your repository location
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.bookshop")
    public DataSource secondaryDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/bookshop");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(secondaryDataSource());
        factory.setPackagesToScan("com.abc.bank.mortgage.bookshop.entity"); // Adjust to your entity package
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Set Hibernate properties explicitly
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Use MySQLDialect or MySQL8Dialect
        jpaProperties.put("hibernate.hbm2ddl.auto", "update"); // Match primary DDL strategy
        jpaProperties.put("hibernate.show_sql", "true"); // Optional: Log SQL queries
        jpaProperties.put("hibernate.format_sql", "true"); // Optional: Format SQL for readability
        factory.setJpaProperties(jpaProperties);


        return factory;
    }

    @Bean
    public JpaTransactionManager secondaryTransactionManager(EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}
