/**
 * 
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class PersonController {

	@Autowired
	private PersonSvc personaSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/person")
	public ResponseEntity<List<PersonDto>> getAllPersons(@RequestParam(value = "id", required = false) Long personId)
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
				Person person = personaSvc.get(personId).orElseThrow(
						() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
				PersonDto personDto = mapper.map(person, PersonDto.class);
				persontoList.add(personDto);

			}

			if (persontoList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(persontoList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<PersonDto> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personaSvc.get(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		PersonDto personDto = mapper.map(person, PersonDto.class);
		return ResponseEntity.ok().body(personDto);
	}

	@PostMapping("/person")
	public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto personDto) {
		log.info("create Person:........." + personDto);
		Person person = mapper.map(personDto, Person.class);
		log.info("create Person:........." + person);
		mapper.map(personaSvc.save(person), PersonDto.class);
		return new ResponseEntity<>(personDto, HttpStatus.CREATED);
	}

	@PutMapping("/person/{id}")
	public ResponseEntity<PersonDto> updatePerson(@PathVariable(value = "id") long personId,
			@Valid @RequestBody PersonDto personDto) throws ResourceNotFoundException {
		Person person = personaSvc.get(personId).orElseThrow(
				() -> new ResourceNotFoundException("Person not found for this id to update :: " + personId));
		log.info("Update Person:........." + personDto);
		// Assigning values from request
		mapper.map(personDto, person);
		//Reset Id / Primary key from query parameter
		person.setPersonid(personId);
		log.info("Update Person:........." + person);
		return ResponseEntity.ok(mapper.map(personaSvc.save(person), PersonDto.class));

	}

	@DeleteMapping("/person/{id}")
	public ResponseEntity<HttpStatus> deletePerson(@PathVariable(value = "id") Long personId) {
		try {
			log.info("Deleting  Person:........." + personId);
			personaSvc.delete(personId);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the person
	// records
	/*
	 * @DeleteMapping("/person") public ResponseEntity<HttpStatus>
	 * deleteAllPersons() { try { log.info("Deleting  All Person:.........");
	 * personSvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
