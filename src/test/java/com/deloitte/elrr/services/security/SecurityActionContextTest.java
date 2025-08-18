package com.deloitte.elrr.services.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
