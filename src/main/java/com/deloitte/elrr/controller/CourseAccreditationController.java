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

import com.deloitte.elrr.dto.CourseAccreditationDto;
import com.deloitte.elrr.entity.CourseAccreditation;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.CourseAccreditationSvc;

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
public class CourseAccreditationController {
    /**
     *
     */
    @Autowired
    private CourseAccreditationSvc courseAccreditationSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;
    /**
     *
     * @param courseAccreditationid
     * @return ResponseEntity<List<CourseAccreditationDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/courseaccreditation")
    public ResponseEntity<List<CourseAccreditationDto>>
        getAllCourseAccreditations(@RequestParam(value = "id", required = false)
        final Long courseAccreditationid)
        throws ResourceNotFoundException {
        try {
            List<CourseAccreditationDto> courseAccreditationList
                    = new ArrayList<>();
            if (courseAccreditationid == null) {
                Iterable<CourseAccreditation> courseAccreditations
                        = courseAccreditationSvc.findAll();

                for (CourseAccreditation courseAccreditation
                        : courseAccreditations) {
                    CourseAccreditationDto courseAccreditationDto = mapper.map(
                            courseAccreditation, CourseAccreditationDto.class);
                    courseAccreditationList.add(courseAccreditationDto);
                }
            } else {
                CourseAccreditation accreditation = courseAccreditationSvc
                        .get(courseAccreditationid)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "CourseAccreditation not found for this id :: "
                                        + courseAccreditationid));
                CourseAccreditationDto courseAccreditationDto = mapper
                        .map(accreditation, CourseAccreditationDto.class);
                courseAccreditationList.add(courseAccreditationDto);

            }

            if (courseAccreditationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(courseAccreditationList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param courseAccreditationid
     * @return ResponseEntity<CourseAccreditationDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/courseaccreditation/{id}")
    public ResponseEntity<CourseAccreditationDto> getCourseAccreditationById(
            @PathVariable(value = "id") final Long courseAccreditationid)
            throws ResourceNotFoundException {
        log.info("Update request id:........." + courseAccreditationid);
        log.info("courseaccreditation:........." + courseAccreditationid);
        log.info("Update courseaccreditation:.........");
        CourseAccreditation acceditation = courseAccreditationSvc
                .get(courseAccreditationid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CourseAccreditation not found for this id :: "
                                + courseAccreditationid));
        CourseAccreditationDto courseAccreditationDto = mapper.map(acceditation,
                CourseAccreditationDto.class);
        return ResponseEntity.ok().body(courseAccreditationDto);
    }
    /**
     *
     * @param courseAccreditationDto
     * @return ResponseEntity<CourseAccreditationDto>
     */
    @PostMapping("/courseaccreditation")
    public ResponseEntity<CourseAccreditationDto> createCourseAccreditation(
    @Valid @RequestBody final CourseAccreditationDto courseAccreditationDto) {
        CourseAccreditation courseAccreditation = mapper
                .map(courseAccreditationDto, CourseAccreditation.class);
        mapper.map(courseAccreditationSvc.save(courseAccreditation),
                CourseAccreditationDto.class);
        return new ResponseEntity<>(courseAccreditationDto, HttpStatus.CREATED);
    }
    /**
     *
     * @param courseAccreditationid
     * @param courseAccreditationDto
     * @return ResponseEntity<CourseAccreditationDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/courseaccreditation/{id}")
    public ResponseEntity<CourseAccreditationDto> updateCourseAccreditation(
            @PathVariable(value = "id") final long courseAccreditationid,
        @Valid @RequestBody final CourseAccreditationDto courseAccreditationDto)
            throws ResourceNotFoundException {
        log.info("Update request id:........." + courseAccreditationid);
        log.info("courseaccreditation:........." + courseAccreditationid);
        log.info("Update courseaccreditation:.........");
        CourseAccreditation courseAccreditation = courseAccreditationSvc
                .get(courseAccreditationid)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "CourseAccreditation not found for this id to update :: "
                                + courseAccreditationid));
        log.info("Update CourseAccreditation:........."
                + courseAccreditationDto);
        // Assigning values from request
        mapper.map(courseAccreditationDto, courseAccreditation);
        // Reset Id / Primary key from query parameter
        courseAccreditation.setCourseaccreditationid(courseAccreditationid);
        log.info("Update Acceditation:........." + courseAccreditation);
        return ResponseEntity
                .ok(mapper.map(courseAccreditationSvc.save(courseAccreditation),
                        CourseAccreditationDto.class));

    }
    /**
     *
     * @param courseAccreditationid
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/courseaccreditation/{id}")
    public ResponseEntity<HttpStatus> deleteCourseAccreditation(
            @PathVariable(value = "id") final Long courseAccreditationid) {
        try {
            log.info("deleting request id:........." + courseAccreditationid);
            log.info("Deleting  CourseAccreditation:........."
                    + courseAccreditationid);
            courseAccreditationSvc.delete(courseAccreditationid);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
