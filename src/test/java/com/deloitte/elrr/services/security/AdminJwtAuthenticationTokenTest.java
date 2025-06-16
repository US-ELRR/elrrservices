package com.deloitte.elrr.services.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;

class AdminJwtAuthenticationTokenTest {

    private AdminJwtAuthenticationToken adminJwtAuthToken;
    private List<SystemAuthority> authorities;
    private String testToken;
    private DecodedJWT jwt;

    @BeforeEach
    void setUp() {
        // Create a JWT token with admin role
        JwtUtil jwtUtil = new JwtUtil("test-secret");
        testToken = jwtUtil.createToken();
        jwt = jwtUtil.verify(testToken);

        // Set up authentication token
        SystemAuthority authority = new SystemAuthority(
                SystemAuthority.SystemRole.ROLE_ADMIN);
        authorities = Collections.singletonList(authority);
        adminJwtAuthToken = new AdminJwtAuthenticationToken(authorities, jwt);
    }

    @Test
    void testGetCredentials() {
        // Act
        String credentials = (String) adminJwtAuthToken.getCredentials();

        // Assert
        assertNotNull(credentials);
        assertEquals(testToken, credentials);
    }

    @Test
    void testGetPrincipal() {
        // Act
        Object principal = adminJwtAuthToken.getPrincipal();

        // Assert
        assertNotNull(principal);
    }

    @Test
    void testGetClaim() {
        // Act
        Object claim = adminJwtAuthToken.getClaim("elrr_permissions");

        // Assert
        assertNotNull(claim);
    }

    @Test
    void testIsAuthenticated() {
        // Act & Assert
        assertTrue(adminJwtAuthToken.isAuthenticated());
    }
}
