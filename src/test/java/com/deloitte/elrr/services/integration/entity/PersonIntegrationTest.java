package com.deloitte.elrr.services.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
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
@EnabledIfSystemProperty(named = "elrr.integration.tests", matches = "true")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    @Transactional
    void testCreateAndRetrievePerson() {
        // Arrange
        Person person = createTestPerson();

        // Act
        Person savedPerson = personRepository.save(person);

        // Assert
        assertNotNull(savedPerson.getId());
        assertThat(savedPerson.getFirstName()).isEqualTo("John");
        assertThat(savedPerson.getLastName()).isEqualTo("Doe");
        assertThat(savedPerson.getBirthdate()).isEqualTo(LocalDate.of(1990, 1, 1));
    }

    @Test
    @Transactional
    void testPersonWithLocation() {
        // Arrange
        Location location = createTestLocation();
        Location savedLocation = locationRepository.save(location);

        Person person = createTestPerson();
        person.setMailingAddress(savedLocation);

        // Act
        Person savedPerson = personRepository.save(person);

        // Assert
        assertNotNull(savedPerson.getId());
        assertNotNull(savedPerson.getMailingAddress());
        assertThat(savedPerson.getMailingAddress().getCity()).isEqualTo("Test City");
    }

    @Test
    @Transactional
    void testFindPersonById() {
        // Arrange
        Person person = createTestPerson();
        Person savedPerson = personRepository.save(person);

        // Act
        Optional<Person> foundPerson = personRepository.findById(savedPerson.getId());

        // Assert
        assertTrue(foundPerson.isPresent());
        assertThat(foundPerson.get().getFirstName()).isEqualTo("John");
        assertThat(foundPerson.get().getLastName()).isEqualTo("Doe");
    }

    @Test
    @Transactional
    void testUpdatePerson() {
        // Arrange
        Person person = createTestPerson();
        Person savedPerson = personRepository.save(person);

        // Act
        savedPerson.setFirstName("Jane");
        savedPerson.setLastModified(LocalDateTime.now());
        Person updatedPerson = personRepository.save(savedPerson);

        // Assert
        assertThat(updatedPerson.getFirstName()).isEqualTo("Jane");
        assertThat(updatedPerson.getLastName()).isEqualTo("Doe");
    }

    @Test
    @Transactional
    void testDeletePerson() {
        // Arrange
        Person person = createTestPerson();
        Person savedPerson = personRepository.save(person);
        UUID personId = savedPerson.getId();

        // Act
        personRepository.delete(savedPerson);

        // Assert
        Optional<Person> deletedPerson = personRepository.findById(personId);
        assertThat(deletedPerson).isEmpty();
    }

    @Test
    @Transactional
    void testPersonValidation() {
        // Arrange
        // Test that Person can be saved with minimal required data
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("User");

        // Act
        Person savedPerson = personRepository.save(person);

        // Assert
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
