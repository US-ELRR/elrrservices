/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

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
public class CourseController {
    /**
     *
     */
    @Autowired
    private CourseSvc courseSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;
    /**
     *
     * @param courseId
     * @return ResponseEntity<List<CourseDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/course")
    public ResponseEntity<List<CourseDto>> getAllCourses(
            @RequestParam(value = "id", required = false) final Long courseId)
            throws ResourceNotFoundException {
        try {
            log.info("getAllCourses course id:........." + courseId);
            log.info("getAllCourses:........." + courseId);
            log.info("getAllCourses :.........");
            List<CourseDto> courseList = new ArrayList<>();
            if (courseId == null) {
                Iterable<Course> courses = courseSvc.findAll();

                for (Course course : courses) {
                    CourseDto courseDto = mapper.map(course, CourseDto.class);
                    courseList.add(courseDto);
                }
            } else {
                Course course = courseSvc.get(courseId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Course not found for this id :: " + courseId));
                CourseDto courseDto = mapper.map(course, CourseDto.class);
                courseList.add(courseDto);

            }

            if (courseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(courseList);
            }
        } catch (Exception e) {
            log.error("Error occurred when getting all courses: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param courseId
     * @return ResponseEntity<CourseDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/course/{id}")
    public ResponseEntity<CourseDto> getCourseById(
            @PathVariable(value = "id") final Long courseId)
            throws ResourceNotFoundException {
        Course course = courseSvc.get(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found for this id :: " + courseId));
        CourseDto courseDto = mapper.map(course, CourseDto.class);
        return ResponseEntity.ok().body(courseDto);
    }
    /**
     *
     * @param courseDto
     * @return ResponseEntity<CourseDto>
     */
    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(
            @Valid @RequestBody final CourseDto courseDto) {
        log.info("create Course:........." + courseDto);
        Course course = mapper.map(courseDto, Course.class);
        log.info("create Course:........." + course);
        mapper.map(courseSvc.save(course), CourseDto.class);
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
    }
    /**
     *
     * @param courseId
     * @param courseDto
     * @return ResponseEntity<CourseDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/course/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable(value = "id") final long courseId,
            @Valid @RequestBody final CourseDto courseDto)
            throws ResourceNotFoundException {
        Course course = courseSvc.get(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found for this id to update :: "
                                + courseId));
        log.info("Update Course:........." + courseDto);
        // Assigning values from request
        mapper.map(courseDto, course);
        // Reset Id / Primary key from query parameter
        course.setCourseid(courseId);
        log.info("Update Course:........." + course);
        return ResponseEntity
                .ok(mapper.map(courseSvc.save(course), CourseDto.class));

    }
    /**
     *
     * @param courseId
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/course/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(
            @PathVariable(value = "id") final Long courseId) {
        try {
            log.info("Deleting  Course:........." + courseId);
            courseSvc.delete(courseId);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error occurred when deleting course: ", e);
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
