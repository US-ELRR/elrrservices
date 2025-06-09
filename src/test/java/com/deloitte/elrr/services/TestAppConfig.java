package com.deloitte.elrr.services;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.deloitte.elrr.services.security.JwtUtil;

@TestConfiguration
public class TestAppConfig {

    /**
     * Creates test JwtUtil Bean for use in tests.
     * @return JwtUtil for tests
     */
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil("secretsecretsecretshhh");
    }

    /**
     *
     */
    void contextLoads() {
        //empty for now, but needed for future global loading
    }

}
