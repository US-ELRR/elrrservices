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

import com.deloitte.elrr.dto.CourseAccreditationDto;
import com.deloitte.elrr.entity.CourseAccreditation;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.CourseAccreditationSvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@RestController
@RequestMapping("api")
@Slf4j
public class CourseAccreditationController {

	@Autowired
	private CourseAccreditationSvc courseAccreditationSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/courseaccreditation")
	public ResponseEntity<List<CourseAccreditationDto>> getAllCourseAccreditations(
			@RequestParam(value = "id", required = false) Long courseAccreditationid) throws ResourceNotFoundException {
		try {
			List<CourseAccreditationDto> courseAccreditationList = new ArrayList<>();
			if (courseAccreditationid == null) {
				Iterable<CourseAccreditation> courseAccreditations = courseAccreditationSvc.findAll();

				for (CourseAccreditation courseAccreditation : courseAccreditations) {
					CourseAccreditationDto courseAccreditationDto = mapper.map(courseAccreditation,
							CourseAccreditationDto.class);
					courseAccreditationList.add(courseAccreditationDto);
				}
			} else {
				CourseAccreditation accreditation = courseAccreditationSvc.get(courseAccreditationid)
						.orElseThrow(() -> new ResourceNotFoundException(
								"CourseAccreditation not found for this id :: " + courseAccreditationid));
				CourseAccreditationDto courseAccreditationDto = mapper.map(accreditation, CourseAccreditationDto.class);
				courseAccreditationList.add(courseAccreditationDto);

			}

			if (courseAccreditationList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(courseAccreditationList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/courseaccreditation/{id}")
	public ResponseEntity<CourseAccreditationDto> getCourseAccreditationById(@PathVariable(value = "id") Long courseAccreditationid)
			throws ResourceNotFoundException {
		CourseAccreditation acceditation = courseAccreditationSvc.get(courseAccreditationid).orElseThrow(
				() -> new ResourceNotFoundException("CourseAccreditation not found for this id :: " + courseAccreditationid));
		CourseAccreditationDto courseAccreditationDto = mapper.map(acceditation, CourseAccreditationDto.class);
		return ResponseEntity.ok().body(courseAccreditationDto);
	}

	@PostMapping("/courseaccreditation")
	public ResponseEntity<CourseAccreditationDto> createCourseAccreditation(
			@Valid @RequestBody CourseAccreditationDto courseAccreditationDto) {
		log.info("create CourseAccreditation:........." + courseAccreditationDto);
		CourseAccreditation courseAccreditation = mapper.map(courseAccreditationDto, CourseAccreditation.class);
		log.info("create courseaccreditation:........." + courseAccreditation);
		mapper.map(courseAccreditationSvc.save(courseAccreditation), CourseAccreditationDto.class);
		return new ResponseEntity<>(courseAccreditationDto, HttpStatus.CREATED);
	}

	@PutMapping("/courseaccreditation/{id}")
	public ResponseEntity<CourseAccreditationDto> updateCourseAccreditation(@PathVariable(value = "id") long courseAccreditationid,
			@Valid @RequestBody CourseAccreditationDto courseAccreditationDto) throws ResourceNotFoundException {
		CourseAccreditation courseAccreditation = courseAccreditationSvc.get(courseAccreditationid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"CourseAccreditation not found for this id to update :: " + courseAccreditationid));
		log.info("Update CourseAccreditation:........." + courseAccreditationDto);
		// Assigning values from request
		mapper.map(courseAccreditationDto, courseAccreditation);
		// Reset Id / Primary key from query parameter
		courseAccreditation.setCourseaccreditationid(courseAccreditationid);
		log.info("Update Acceditation:........." + courseAccreditation);
		return ResponseEntity
				.ok(mapper.map(courseAccreditationSvc.save(courseAccreditation), CourseAccreditationDto.class));

	}

	@DeleteMapping("/courseaccreditation/{id}")
	public ResponseEntity<HttpStatus> deleteCourseAccreditation(@PathVariable(value = "id") Long courseAccreditationid) {
		try {
			log.info("Deleting  CourseAccreditation:........." + courseAccreditationid);
			courseAccreditationSvc.delete(courseAccreditationid);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the
	// accreditation
	// records
	/*
	 * @DeleteMapping("/accreditation") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try {
	 * log.info("Deleting  All CourseAccreditation:.........");
	 * accreditationSvc.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
