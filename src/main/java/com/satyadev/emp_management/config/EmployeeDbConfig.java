package com.satyadev.emp_management.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.satyadev.emp_management.repository",
        entityManagerFactoryRef = "employeeManagerFactory",
        transactionManagerRef = "employeeTransactionManager"
)
public class EmployeeDbConfig {

    @Bean
    @ConfigurationProperties("employee.datasource")
    public DataSource employeeDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean employeeManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDataSource") DataSource dataSource
    ){
        return builder
                .dataSource(dataSource)
                .packages("com.satyadev.emp_management.entity")
                .persistenceUnit("employee")
                .build();
    }

    @Bean
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
