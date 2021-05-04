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

import com.deloitte.elrr.dto.CourseDto;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.CourseSvc;
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
public class CourseController {

	@Autowired
	private CourseSvc courseSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/course")
	public ResponseEntity<List<CourseDto>> getAllCourses(@RequestParam(value = "id", required = false) Long courseId)
			throws ResourceNotFoundException {
		try {
			List<CourseDto> courseList = new ArrayList<>();
			if (courseId == null) {
				Iterable<Course> Courses = courseSvc.findAll();

				for (Course course : Courses) {
					CourseDto courseDto = mapper.map(course, CourseDto.class);
					courseList.add(courseDto);
				}
			} else {
				Course course = courseSvc.get(courseId).orElseThrow(
						() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
				CourseDto courseDto = mapper.map(course, CourseDto.class);
				courseList.add(courseDto);

			}

			if (courseList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(courseList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<CourseDto> getCourseById(@PathVariable(value = "id") Long courseId)
			throws ResourceNotFoundException {
		Course course = courseSvc.get(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
		CourseDto courseDto = mapper.map(course, CourseDto.class);
		return ResponseEntity.ok().body(courseDto);
	}

	@PostMapping("/course")
	public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto courseDto) {
		log.info("create Course:........." + courseDto);
		Course course = mapper.map(courseDto, Course.class);
		log.info("create Course:........." + course);
		mapper.map(courseSvc.save(course), CourseDto.class);
		return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
	}

	@PutMapping("/course/{id}")
	public ResponseEntity<CourseDto> updateCourse(@PathVariable(value = "id") long courseId,
			@Valid @RequestBody CourseDto courseDto) throws ResourceNotFoundException {
		Course course = courseSvc.get(courseId).orElseThrow(
				() -> new ResourceNotFoundException("Course not found for this id to update :: " + courseId));
		log.info("Update Course:........." + courseDto);
		// Assigning values from request
		mapper.map(courseDto, course);
		//Reset Id / Primary key from query parameter
		course.setCourseid(courseId);
		log.info("Update Course:........." + course);
		return ResponseEntity.ok(mapper.map(courseSvc.save(course), CourseDto.class));

	}

	@DeleteMapping("/course/{id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable(value = "id") Long courseId) {
		try {
			log.info("Deleting  Course:........." + courseId);
			courseSvc.delete(courseId);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the course
	// records
	/*
	 * @DeleteMapping("/course") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try { log.info("Deleting  All Course:.........");
	 * courseSvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
