package com.deloitte.elrr.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deloitte.elrr.util.ValueObjectTestUtility;

/**
 * Test class for ExtensibleDto
 */
class ExtensibleDtoTest {

    private TestExtensibleDto testExtensibleDto;

    /**
     * Concrete implementation of ExtensibleDto for testing purposes
     */
    private static class TestExtensibleDto extends ExtensibleDto {
        // Simple concrete implementation for testing
    }

    @BeforeEach
    void setUp() {
        testExtensibleDto = new TestExtensibleDto();
    }

    /**
     * Test accessor methods using ValueObjectTestUtility
     */
    @Test
    void testAccessors() {
        ValueObjectTestUtility.validateAccessors(TestExtensibleDto.class);
    }

    /**
     * Test extensions field initialization
     */
    @Test
    void testExtensionsInitialization() {
        assertNull(testExtensibleDto.getExtensions());
    }

    /**
     * Test setting and getting extensions with IRI keys
     */
    @Test
    void testExtensionsSetterGetter() {
        Map<URI, Object> extensions = new HashMap<>();
        extensions.put(URI.create("https://example.org/schema#customField"), "test value");

        testExtensibleDto.setExtensions(extensions);

        assertNotNull(testExtensibleDto.getExtensions());
        assertEquals("test value", testExtensibleDto.getExtensions().get(URI.create("https://example.org/schema#customField")));
    }

    /**
     * Test toString method
     */
    @Test
    void testToString() {
        assertNotNull(testExtensibleDto.toString());
    }
}
