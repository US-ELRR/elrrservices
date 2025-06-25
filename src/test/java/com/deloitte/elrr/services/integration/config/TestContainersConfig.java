package com.deloitte.elrr.services.integration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * TestContainers configuration for integration tests.
 * Provides PostgreSQL container for database testing.
 */
@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfig {

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.4"))
                .withDatabaseName("elrr_test")
                .withUsername("testuser")
                .withPassword("testpass")
                .withInitScript("test-schema.sql");
    }
}
