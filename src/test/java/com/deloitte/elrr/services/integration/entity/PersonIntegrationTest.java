package com.deloitte.elrr.services.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.elrr.entity.Location;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.repository.LocationRepository;
import com.deloitte.elrr.repository.PersonRepository;
import com.deloitte.elrr.services.integration.BaseIntegrationTest;

/**
 * Integration tests for Person entity.
 * Tests entity persistence, relationships, and behavior.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    @Transactional
    void testCreateAndRetrievePerson() {
        // Given
        Person person = createTestPerson();

        // When
        Person savedPerson = personRepository.save(person);

        // Then
        assertNotNull(savedPerson.getId());
        assertThat(savedPerson.getFirstName()).isEqualTo("John");
        assertThat(savedPerson.getLastName()).isEqualTo("Doe");
        assertThat(savedPerson.getBirthdate()).isEqualTo(LocalDate.of(1990, 1, 1));
    }

    @Test
    @Transactional
    void testPersonWithLocation() {
        // Given
        Location location = createTestLocation();
        Location savedLocation = locationRepository.save(location);

        Person person = createTestPerson();
        person.setMailingAddress(savedLocation);

        // When
        Person savedPerson = personRepository.save(person);

        // Then
        assertNotNull(savedPerson.getId());
        assertNotNull(savedPerson.getMailingAddress());
        assertThat(savedPerson.getMailingAddress().getCity()).isEqualTo("Test City");
    }

    @Test
    @Transactional
    void testFindPersonById() {
        // Given
        Person person = createTestPerson();
        Person savedPerson = personRepository.save(person);

        // When
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());

        // Then
        assertTrue(foundPerson.isPresent());
        assertThat(foundPerson.get().getFirstName()).isEqualTo("John");
        assertThat(foundPerson.get().getLastName()).isEqualTo("Doe");
    }

    @Test
    @Transactional
    void testUpdatePerson() {
        // Given
        Person person = createTestPerson();
        Person savedPerson = personRepository.save(person);

        // When
        savedPerson.setFirstName("Jane");
        savedPerson.setLastModified(LocalDateTime.now());
        Person updatedPerson = personRepository.save(savedPerson);

        // Then
        assertThat(updatedPerson.getFirstName()).isEqualTo("Jane");
        assertThat(updatedPerson.getLastName()).isEqualTo("Doe");
    }

    @Test
    @Transactional
    void testDeletePerson() {
        // Given
        Person person = createTestPerson();
        Person savedPerson = personRepository.save(person);
        UUID personId = savedPerson.getId();

        // When
        personRepository.delete(savedPerson);

        // Then
        Optional<Person> deletedPerson = personRepository.findById(personId);
        assertThat(deletedPerson).isEmpty();
    }

    @Test
    @Transactional
    void testPersonValidation() {
        // Test that Person can be saved with minimal required data
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("User");

        // When
        Person savedPerson = personRepository.save(person);

        // Then
        assertNotNull(savedPerson.getId());
        assertThat(savedPerson.getFirstName()).isEqualTo("Test");
        assertThat(savedPerson.getLastName()).isEqualTo("User");
    }

    private Person createTestPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setName("John Doe");
        person.setBirthdate(LocalDate.of(1990, 1, 1));
        person.setCitizenship("US");
        person.setPrimaryLanguage("English");
        person.setUpdatedBy("test");
        return person;
    }

    private Location createTestLocation() {
        Location location = new Location();
        location.setStreetNumberAndName("123 Test St");
        location.setCity("Test City");
        location.setStateAbbreviation("TX");
        location.setPostalCode("12345");
        location.setCountryCode("US");
        location.setUpdatedBy("test");
        return location;
    }
}
