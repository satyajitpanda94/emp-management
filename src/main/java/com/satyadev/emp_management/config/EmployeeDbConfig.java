package com.satyadev.emp_management.config;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.satyadev.emp_management.repository.employee",
        entityManagerFactoryRef = "employeeManagerFactory",
        transactionManagerRef = "employeeTransactionManager"
)
public class EmployeeDbConfig {

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {

        JpaVendorAdapter jpaVendorAdapter =
                new HibernateJpaVendorAdapter();

        return new EntityManagerFactoryBuilder(
                jpaVendorAdapter,
                dataSource -> Map.of(),
                null
        );
    }

    @Bean
    @ConfigurationProperties("employee.datasource")
    public DataSource employeeDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean employeeManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.satyadev.emp_management.entity.employee")
                .persistenceUnit("employee")
                .properties(Map.of(
                        "hibernate.hbm2ddl.auto", "update",
                        "hibernate.show_sql", "true",
                        "hibernate.format_sql", "true"
                ))
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}