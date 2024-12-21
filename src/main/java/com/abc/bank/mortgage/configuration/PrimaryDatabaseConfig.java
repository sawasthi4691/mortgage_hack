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
        basePackages = "com.abc.bank.mortgage.repository", // Adjust the package to your repository location
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mortgage");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(primaryDataSource());
        factory.setPackagesToScan("com.abc.bank.mortgage.models"); // Adjust to your entity package
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
    public JpaTransactionManager primaryTransactionManager(EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}

