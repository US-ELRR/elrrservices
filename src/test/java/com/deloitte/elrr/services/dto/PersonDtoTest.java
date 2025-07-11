package com.deloitte.elrr.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.deloitte.elrr.util.ValueObjectTestUtility;

class PersonDtoTest {

    /**
    *
    */
    @Test
    void test() {
        ValueObjectTestUtility.validateAccessors(PersonDto.class);
    }

    /**
     *
     */
    @Test
    void testToString() {
        assertNotNull(new PersonDto().toString());
    }

    /**
     * Test that PersonDto can handle extensions
     */
    @Test
    void testPersonDtoExtensions() {
        PersonDto person = new PersonDto();
        Map<URI, Object> extensions = new HashMap<>();
        extensions.put(URI.create("https://example.org/schema#socialSecurityNumber"), "123-45-6789");
        extensions.put(URI.create("https://example.org/schema#preferredName"), "Mike");

        person.setExtensions(extensions);
        
        assertNotNull(person.getExtensions());
        assertEquals("123-45-6789", person.getExtensions().get(URI.create("https://example.org/schema#socialSecurityNumber")));
        assertEquals("Mike", person.getExtensions().get(URI.create("https://example.org/schema#preferredName")));
    }
}
