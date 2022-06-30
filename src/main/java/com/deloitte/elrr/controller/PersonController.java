/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.PersonSvc;
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
    private PersonSvc personaSvc;
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
            @RequestParam(value = "id", required = false) final Long personId)
            throws ResourceNotFoundException {
        try {
            List<PersonDto> persontoList = new ArrayList<>();
            if (personId == null) {
                Iterable<Person> persons = personaSvc.findAll();

                for (Person person : persons) {
                    PersonDto personDto = mapper.map(person, PersonDto.class);
                    persontoList.add(personDto);
                }
            } else {
                Person person = personaSvc.get(personId)
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
            @PathVariable(value = "id") final Long personId)
            throws ResourceNotFoundException {
        Person person = personaSvc.get(personId)
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
        log.info("create Person:........." + personDto);
        Person person = mapper.map(personDto, Person.class);
        log.info("create Person:........." + person);
        mapper.map(personaSvc.save(person), PersonDto.class);
        return new ResponseEntity<>(personDto, HttpStatus.CREATED);
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
            @PathVariable(value = "id") final long personId,
            @Valid @RequestBody final PersonDto personDto)
            throws ResourceNotFoundException {
        Person person = personaSvc.get(personId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person not found for this id to update :: "
                                + personId));
        log.info("Update Person:........." + personDto);
        // Assigning values from request
        mapper.map(personDto, person);
        // Reset Id / Primary key from query parameter
        person.setPersonid(personId);
        log.info("Update Person:........." + person);
        return ResponseEntity
                .ok(mapper.map(personaSvc.save(person), PersonDto.class));

    }
    /**
     *
     * @param personId
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/person/{id}")
    public ResponseEntity<HttpStatus> deletePerson(
            @PathVariable(value = "id") final Long personId) {
        try {
            log.info("Deleting  Person:........." + personId);
            personaSvc.delete(personId);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
