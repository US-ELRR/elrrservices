package com.deloitte.elrr.services.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.elrr.services.dto.PermissionDto;
import com.deloitte.elrr.services.dto.PermissionDto.Action;

@ExtendWith(MockitoExtension.class)
class CustomPermissionEvaluatorTest {

    private CustomPermissionEvaluator permissionEvaluator;

    @Mock
    private JwtAuthenticationToken mockToken;

    @BeforeEach
    void setUp() {
        permissionEvaluator = new CustomPermissionEvaluator();
    }

    @Test
    void hasPermission_WithValidPermission_ReturnsTrue() {
        // Arrange
        PermissionDto permission = new PermissionDto("users", null, Arrays.asList(Action.READ));
        when(mockToken.getPermissions()).thenReturn(Arrays.asList(permission));

        // Act
        boolean result = permissionEvaluator.hasPermission(
            mockToken, "users", Action.READ);

        // Assert
        assertTrue(result);
    }

    @Test
    void hasPermission_WithInvalidResource_ReturnsFalse() {
        // Arrange
        PermissionDto permission = new PermissionDto("users", null, Arrays.asList(Action.READ));
        when(mockToken.getPermissions()).thenReturn(Arrays.asList(permission));

        // Act
        boolean result = permissionEvaluator.hasPermission(
            mockToken, "courses", Action.READ);

        // Assert
        assertFalse(result);
    }

    @Test
    void hasPermission_WithNullPermissions_ReturnsFalse() {
        // Arrange
        when(mockToken.getPermissions()).thenReturn(null);

        // Act
        boolean result = permissionEvaluator.hasPermission(
            mockToken, "users", Action.READ);

        // Assert
        assertFalse(result);
    }

    @Test
    void hasPermission_WithEmptyPermissions_ReturnsFalse() {
        // Arrange
        when(mockToken.getPermissions()).thenReturn(Collections.emptyList());

        // Act
        boolean result = permissionEvaluator.hasPermission(
            mockToken, "users", Action.READ);

        // Assert
        assertFalse(result);
    }
}
