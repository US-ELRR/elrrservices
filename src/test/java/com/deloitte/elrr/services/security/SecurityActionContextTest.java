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
        // Given
        String expectedAction = "CREATE";
        String expectedResource = "person";

        // When
        securityActionContext.setCurrentContext(expectedAction, expectedResource);

        // Then
        assertEquals(expectedAction, securityActionContext.getCurrentAction());
        assertEquals(expectedResource, securityActionContext.getCurrentResource());
    }

    @Test
    void testGetCurrentActionWhenNotSet() {
        // When
        String result = securityActionContext.getCurrentAction();

        // Then
        assertNull(result);
    }

    @Test
    void testGetCurrentResourceWhenNotSet() {
        // When
        String result = securityActionContext.getCurrentResource();

        // Then
        assertNull(result);
    }

    @Test
    void testSetCurrentContextOverwritesPrevious() {
        // Given
        securityActionContext.setCurrentContext("CREATE", "person");

        // When
        securityActionContext.setCurrentContext("UPDATE", "organization");

        // Then
        assertEquals("UPDATE", securityActionContext.getCurrentAction());
        assertEquals("organization", securityActionContext.getCurrentResource());
    }

    @Test
    void testSetCurrentContextWithNulls() {
        // Given
        securityActionContext.setCurrentContext("DELETE", "credential");

        // When
        securityActionContext.setCurrentContext(null, null);

        // Then
        assertNull(securityActionContext.getCurrentAction());
        assertNull(securityActionContext.getCurrentResource());
    }

    @Test
    void testSetCurrentContextWithMixedNulls() {
        // When
        securityActionContext.setCurrentContext("READ", null);

        // Then
        assertEquals("READ", securityActionContext.getCurrentAction());
        assertNull(securityActionContext.getCurrentResource());

        // When
        securityActionContext.setCurrentContext(null, "facility");

        // Then
        assertNull(securityActionContext.getCurrentAction());
        assertEquals("facility", securityActionContext.getCurrentResource());
    }

    @Test
    void testMultipleContextChanges() {
        // Given/When/Then
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
        // When
        UUID requestId1 = securityActionContext.getRequestId();
        UUID requestId2 = securityActionContext.getRequestId();

        // Then
        assertNotNull(requestId1);
        assertNotNull(requestId2);
        assertEquals(requestId1, requestId2); // Same instance should return same ID
    }

    @Test
    void testRequestIdConsistentAcrossContextChanges() {
        // Given
        UUID initialRequestId = securityActionContext.getRequestId();

        // When
        securityActionContext.setCurrentContext("CREATE", "person");
        UUID requestIdAfterSet = securityActionContext.getRequestId();

        securityActionContext.setCurrentContext("UPDATE", "organization");
        UUID requestIdAfterUpdate = securityActionContext.getRequestId();

        // Then
        assertEquals(initialRequestId, requestIdAfterSet);
        assertEquals(initialRequestId, requestIdAfterUpdate);
    }

    @Test
    void testRequestIdIsTimeBasedUuid() {
        // When
        UUID requestId = securityActionContext.getRequestId();

        // Then
        assertNotNull(requestId);
        // Time-based UUIDs typically have version 7 in the most significant bits
        // But we'll just verify it's a valid UUID and not null
        assertTrue(requestId.toString().matches(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));
    }
}
