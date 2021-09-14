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

import com.deloitte.elrr.dto.EmploymentDto;
import com.deloitte.elrr.entity.Employment;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.EmploymentSvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {"http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001", "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class EmploymentController {

	@Autowired
	private EmploymentSvc employmentSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/employment")
	public ResponseEntity<List<EmploymentDto>> getAllEmployments(@RequestParam(value = "id", required = false) Long employmentid)
			throws ResourceNotFoundException {
		try {
			List<EmploymentDto> employmentList = new ArrayList<>();
			if (employmentid == null) {
				Iterable<Employment> employments = employmentSvc.findAll();

				for (Employment employment : employments) {
					EmploymentDto employmentDto = mapper.map(employment, EmploymentDto.class);
					employmentList.add(employmentDto);
				}
			} else {
				Employment employment = employmentSvc.get(employmentid).orElseThrow(
						() -> new ResourceNotFoundException("Employment not found for this id :: " + employmentid));
				EmploymentDto employmentDto = mapper.map(employment, EmploymentDto.class);
				employmentList.add(employmentDto);

			}

			if (employmentList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(employmentList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employment/{id}")
	public ResponseEntity<EmploymentDto> getEmploymentById(@PathVariable(value = "id") Long employmentid)
			throws ResourceNotFoundException {
		Employment employment = employmentSvc.get(employmentid)
				.orElseThrow(() -> new ResourceNotFoundException("Employment not found for this id :: " + employmentid));
		EmploymentDto employmentDto = mapper.map(employment, EmploymentDto.class);
		return ResponseEntity.ok().body(employmentDto);
	}

	@PostMapping("/employment")
	public ResponseEntity<EmploymentDto> createEmployment(@Valid @RequestBody EmploymentDto employmentDto) {
		log.info("create Employment:........." + employmentDto);
		Employment employment = mapper.map(employmentDto, Employment.class);
		log.info("create Employment:........." + employment);
		mapper.map(employmentSvc.save(employment), EmploymentDto.class);
		return new ResponseEntity<>(employmentDto, HttpStatus.CREATED);
	}

	@PutMapping("/employment/{id}")
	public ResponseEntity<EmploymentDto> updateEmployment(@PathVariable(value = "id") long employmentid,
			@Valid @RequestBody EmploymentDto employmentDto) throws ResourceNotFoundException {
		Employment employment = employmentSvc.get(employmentid).orElseThrow(
				() -> new ResourceNotFoundException("Employment not found for this id to update :: " + employmentid));
		log.info("Update Employment:........." + employmentDto);
		// Assigning values from request
		mapper.map(employmentDto, employment);
		//Reset Id / Primary key from query parameter
		employment.setEmploymentid(employmentid);
		log.info("Update Employment:........." + employment);
		return ResponseEntity.ok(mapper.map(employmentSvc.save(employment), EmploymentDto.class));

	}

	@DeleteMapping("/employment/{id}")
	public ResponseEntity<HttpStatus> deleteEmployment(@PathVariable(value = "id") Long employmentid) {
		try {
			log.info("Deleting  Employment:........." + employmentid);
			employmentSvc.delete(employmentid);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the employment
	// records
	/*
	 * @DeleteMapping("/employment") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try { log.info("Deleting  All Employment:.........");
	 * employmentSvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
