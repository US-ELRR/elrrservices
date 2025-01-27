/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.elrr.dto.PersonDto;
import com.deloitte.elrr.dto.PhoneDto;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.Phone;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.PersonSvc;
import com.deloitte.elrr.jpa.svc.PhoneSvc;
import com.jayway.jsonpath.PathNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class PersonController {
    /**
     *
     */
    @Autowired
    private PersonSvc personSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param personId
     * @return ResponseEntity<List<PersonDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/person")
    public ResponseEntity<List<PersonDto>> getAllPersons(
            @RequestParam(value = "id", required = false) final UUID personId)
            throws ResourceNotFoundException {
        try {
            log.info("getting  PersonDto:.........");
            log.info("getting Person id:........." + personId);
            List<PersonDto> persontoList = new ArrayList<>();
            if (personId == null) {
                Iterable<Person> persons = personSvc.findAll();

                for (Person person : persons) {
                    PersonDto personDto = mapper.map(person, PersonDto.class);
                    persontoList.add(personDto);
                }
            } else {
                Person person = personSvc.get(personId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Person not found for this id :: " + personId));
                PersonDto personDto = mapper.map(person, PersonDto.class);
                persontoList.add(personDto);

            }

            if (persontoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(persontoList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param personId
     * @return ResponseEntity<PersonDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDto> getPersonById(
            @PathVariable(value = "id") final UUID personId)
            throws ResourceNotFoundException {
        log.info("getting  Person:.........");
        log.info("getting Person id:........." + personId);
        Person person = personSvc.get(personId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person not found for this id :: " + personId));
        PersonDto personDto = mapper.map(person, PersonDto.class);
        return ResponseEntity.ok().body(personDto);
    }

    /**
     *
     * @param personDto
     * @return ResponseEntity<PersonDto>
     */
    @PostMapping("/person")
    public ResponseEntity<PersonDto> createPerson(
            @Valid @RequestBody final PersonDto personDto) {
        Person person = mapper.map(personDto, Person.class);
        PersonDto response = mapper.map(personSvc.save(person), PersonDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param personId
     * @param personDto
     * @return ResponseEntity<PersonDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDto> updatePerson(
            @PathVariable(value = "id") final UUID personId,
            @Valid @RequestBody final PersonDto personDto)
            throws ResourceNotFoundException {
        log.info("Updating  personId:.........");
        log.info("Updating personId id:........." + personId);
        Person person = personSvc.get(personId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person not found for this id to update :: "
                                + personId));
        log.info("Update Person:........." + personDto);
        // Assigning values from request
        mapper.map(personDto, person);
        // Reset Id / Primary key from query parameter
        person.setId(personId);
        log.info("Update Person:........." + person);
        return ResponseEntity
                .ok(mapper.map(personSvc.save(person), PersonDto.class));

    }

    /**
     *
     * @param personId
     * @return ResponseEntity<HttpStatus>
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/person/{id}")
    public ResponseEntity<HttpStatus> deletePerson(
            @PathVariable(value = "id") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Deleting  Person:.........");
        log.info("Deleting Person id:........." + personId);
        personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id to delete :: " + personId));
        personSvc.delete(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    private PhoneSvc phoneSvc;

    /*
     * Phone
     */
    @PostMapping("/person/{personId}/phone")
    public ResponseEntity<PhoneDto> addPhoneToPerson(
            @PathVariable(value = "personId") final UUID personId,
            @Valid @RequestBody final PhoneDto phoneDto)
            throws ResourceNotFoundException {
        log.info("Adding Phone to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id to delete :: " + personId));
        Phone phone = phoneSvc.save(mapper.map(phoneDto, Phone.class));
        person.getPhoneNumbers().add(phone);
        personSvc.save(person);
        return ResponseEntity.ok(mapper.map(phone, PhoneDto.class));
    }

    @GetMapping("/person/{personId}/phone")
    public ResponseEntity<List<PhoneDto>> getPhones(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting phones for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id to delete :: " + personId));
        return ResponseEntity.ok(person.getPhoneNumbers().stream()
                .map(p -> mapper.map(p, PhoneDto.class))
                .collect(Collectors.toList()));
    }
}
