package com.deloitte.elrr.services.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.deloitte.elrr.services.dto.PermissionDto;

class JwtAuthenticationTokenTest {
    
    private JwtAuthenticationToken jwtAuthToken;
    private List<SystemAuthority> authorities;
    private String testToken;
    private DecodedJWT jwt;
    private List<PermissionDto> permissions = List.of(
            new PermissionDto("resource1", null, List.of(PermissionDto.Action.READ, PermissionDto.Action.UPDATE)),
            new PermissionDto("resource2", null, List.of(PermissionDto.Action.CREATE, PermissionDto.Action.DELETE))
        );

    @BeforeEach
    void setUp() {
        // Create a JWT token with admin role
        JwtUtil jwtUtil = new JwtUtil("test-secret");
        testToken = jwtUtil.createToken(permissions);
        jwt = jwtUtil.verify(testToken);
        
        // Set up authentication token
        SystemAuthority authority = new SystemAuthority(SystemAuthority.SystemRole.ROLE_ADMIN);
        authorities = Collections.singletonList(authority);
        jwtAuthToken = new JwtAuthenticationToken(authorities, jwt);
    }

    @Test
    void testGetCredentials() {
        // Act
        String credentials = (String) jwtAuthToken.getCredentials();

        // Assert
        assertNotNull(credentials);
        assertEquals(testToken, credentials);
    }

    @Test
    void testGetPrincipal() {
        // Act
        Object principal = jwtAuthToken.getPrincipal();

        // Assert 
        assertNotNull(principal);
    }

    @Test 
    void testGetPermissionsClaim() {
        // Act
        Claim permissionsClaim = (Claim)jwtAuthToken.getClaim("elrr_permissions");
        List<PermissionDto> tokenPermissions = permissionsClaim.asList(PermissionDto.class);

        // Assert
        assertNotNull(tokenPermissions);
        assertEquals(permissions.size(), tokenPermissions.size());
        assertTrue(tokenPermissions.containsAll(permissions));
    }

    @Test
    void testGetPermission() {
        // Act
        List<PermissionDto> tokenPermissions = jwtAuthToken.getPermissions();

        // Assert
        assertNotNull(tokenPermissions);
        assertEquals(permissions.size(), tokenPermissions.size());
        assertTrue(tokenPermissions.containsAll(permissions));
    }

    @Test
    void testIsAuthenticated() {
        // Act & Assert
        assertTrue(jwtAuthToken.isAuthenticated());
    }
}
