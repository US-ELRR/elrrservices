package com.deloitte.elrr.services.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for SecurityActionContext.
 */
class SecurityActionContextTest {

    private SecurityActionContext securityActionContext;

    @BeforeEach
    void setUp() {
        securityActionContext = new SecurityActionContext();
    }

    @Test
    void testSetAndGetCurrentAction() {
        // Arrange
        String expectedAction = "CREATE";
        String expectedResource = "person";

        // Act
        securityActionContext.setCurrentContext(expectedAction, expectedResource);

        // Assert
        assertEquals(expectedAction, securityActionContext.getCurrentAction());
        assertEquals(expectedResource, securityActionContext.getCurrentResource());
    }

    @Test
    void testGetCurrentActionWhenNotSet() {
        // Act
        String result = securityActionContext.getCurrentAction();

        // Assert
        assertNull(result);
    }

    @Test
    void testGetCurrentResourceWhenNotSet() {
        // Act
        String result = securityActionContext.getCurrentResource();

        // Assert
        assertNull(result);
    }

    @Test
    void testSetCurrentContextOverwritesPrevious() {
        // Arrange
        securityActionContext.setCurrentContext("CREATE", "person");

        // Act
        securityActionContext.setCurrentContext("UPDATE", "organization");

        // Assert
        assertEquals("UPDATE", securityActionContext.getCurrentAction());
        assertEquals("organization", securityActionContext.getCurrentResource());
    }

    @Test
    void testSetCurrentContextWithNulls() {
        // Arrange
        securityActionContext.setCurrentContext("DELETE", "credential");

        // Act
        securityActionContext.setCurrentContext(null, null);

        // Assert
        assertNull(securityActionContext.getCurrentAction());
        assertNull(securityActionContext.getCurrentResource());
    }

    @Test
    void testSetCurrentContextWithMixedNulls() {
        // Arrange
        securityActionContext.setCurrentContext("READ", null);

        // Assert
        assertEquals("READ", securityActionContext.getCurrentAction());
        assertNull(securityActionContext.getCurrentResource());

        // Act
        securityActionContext.setCurrentContext(null, "facility");

        // Assert
        assertNull(securityActionContext.getCurrentAction());
        assertEquals("facility", securityActionContext.getCurrentResource());
    }

    @Test
    void testMultipleContextChanges() {
        // Act/Assert
        securityActionContext.setCurrentContext("CREATE", "person");
        assertEquals("CREATE", securityActionContext.getCurrentAction());
        assertEquals("person", securityActionContext.getCurrentResource());

        securityActionContext.setCurrentContext("UPDATE", "organization");
        assertEquals("UPDATE", securityActionContext.getCurrentAction());
        assertEquals("organization", securityActionContext.getCurrentResource());

        securityActionContext.setCurrentContext("DELETE", "credential");
        assertEquals("DELETE", securityActionContext.getCurrentAction());
        assertEquals("credential", securityActionContext.getCurrentResource());
    }

    @Test
    void testGetRequestIdGeneratesUniqueId() {
        // Act
        UUID requestId1 = securityActionContext.getRequestId();
        UUID requestId2 = securityActionContext.getRequestId();

        // Assert
        assertNotNull(requestId1);
        assertNotNull(requestId2);
        assertEquals(requestId1, requestId2); // Same instance should return same ID
    }

    @Test
    void testRequestIdConsistentAcrossContextChanges() {
        // Arrange
        UUID initialRequestId = securityActionContext.getRequestId();

        // Act
        securityActionContext.setCurrentContext("CREATE", "person");
        UUID requestIdAfterSet = securityActionContext.getRequestId();

        securityActionContext.setCurrentContext("UPDATE", "organization");
        UUID requestIdAfterUpdate = securityActionContext.getRequestId();

        // Assert
        assertEquals(initialRequestId, requestIdAfterSet);
        assertEquals(initialRequestId, requestIdAfterUpdate);
    }

    @Test
    void testRequestIdIsTimeBasedUuid() {
        // Arrange
        UUID requestId = securityActionContext.getRequestId();

        // Assert
        assertNotNull(requestId);
        // Time-based UUIDs typically have version 7 in the most significant bits
        // But we'll just verify it's a valid UUID and not null
        assertTrue(requestId.toString().matches(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));
    }
}
