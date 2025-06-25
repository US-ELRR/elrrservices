package com.deloitte.elrr.services.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * Base class for integration tests using TestContainers.
 * Provides a PostgreSQL database container for testing entity persistence and behavior.
 */
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {BaseIntegrationTest.Initializer.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class BaseIntegrationTest {

    @Container
    @SuppressWarnings("resource")  // TestContainers handles container lifecycle
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:15.4"))
            .withDatabaseName("elrr_test")
            .withUsername("testuser")
            .withPassword("testpass")
            .withInitScript("test-schema.sql");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword(),
                    "spring.jpa.properties.hibernate.default_schema=services_schema"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
