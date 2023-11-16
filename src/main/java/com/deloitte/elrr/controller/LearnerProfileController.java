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

import com.deloitte.elrr.dto.LearnerProfileDto;
import com.deloitte.elrr.entity.LearnerProfile;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.LearnerProfileSvc;

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
public class LearnerProfileController {
    /**
     *
     */
    @Autowired
    private LearnerProfileSvc learnerProfileFactSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;
    /**
     *
     * @param personid
     * @return ResponseEntity<List<LearnerProfileDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/learnerprofilefact")
    public ResponseEntity<List<LearnerProfileDto>> getAllCourseAccreditations(
            @RequestParam(value = "id", required = false) final Long personid)
            throws ResourceNotFoundException {
        try {
            List<LearnerProfileDto> courseAccreditationList = new ArrayList<>();
            if (personid == null) {
                Iterable<LearnerProfile> courseAccreditations
                = learnerProfileFactSvc.findAll();

                for (LearnerProfile courseAccreditation
                        : courseAccreditations) {
                    LearnerProfileDto learnerProfileFactDto = mapper
                            .map(courseAccreditation, LearnerProfileDto.class);
                    courseAccreditationList.add(learnerProfileFactDto);
                }
            } else {
                LearnerProfile accreditation = learnerProfileFactSvc
                        .get(personid)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "LearnerProfile not found for this id :: "
                                        + personid));
                LearnerProfileDto learnerProfileFactDto = mapper
                        .map(accreditation, LearnerProfileDto.class);
                courseAccreditationList.add(learnerProfileFactDto);

            }

            if (courseAccreditationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(courseAccreditationList);
            }
        } catch (Exception e) {
            log.error("Error occurred when getting learner profile fact: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param personid
     * @return ResponseEntity<LearnerProfileDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/learnerprofilefact/{id}")
    public ResponseEntity<LearnerProfileDto> getCourseAccreditationById(
            @PathVariable(value = "id") final Long personid)
            throws ResourceNotFoundException {
        LearnerProfile learnerProfileFact = learnerProfileFactSvc.get(personid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "LearnerProfile not found for this id :: " + personid));
        LearnerProfileDto learnerProfileFactDto = mapper.map(learnerProfileFact,
                LearnerProfileDto.class);
        return ResponseEntity.ok().body(learnerProfileFactDto);
    }
    /**
     *
     * @param learnerProfileFactDto
     * @return ResponseEntity<LearnerProfileDto>
     */
    @PostMapping("/learnerprofilefact")
    public ResponseEntity<LearnerProfileDto> createCourseAccreditation(
            @Valid @RequestBody final LearnerProfileDto learnerProfileFactDto) {
        LearnerProfile learnerProfileFact = mapper.map(learnerProfileFactDto,
                LearnerProfile.class);
        mapper.map(learnerProfileFactSvc.save(learnerProfileFact),
                LearnerProfileDto.class);
        return new ResponseEntity<>(learnerProfileFactDto, HttpStatus.CREATED);
    }
    /**
     *
     * @param personid
     * @param learnerProfileFactDto
     * @return ResponseEntity<LearnerProfileDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/learnerprofilefact/{id}")
    public ResponseEntity<LearnerProfileDto> updateCourseAccreditation(
            @PathVariable(value = "id") final long personid,
            @Valid @RequestBody final LearnerProfileDto learnerProfileFactDto)
            throws ResourceNotFoundException {
        LearnerProfile learnerProfileFact = learnerProfileFactSvc.get(personid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "LearnerProfile not found for this id to update :: "
                                + personid));
        log.info("Update LearnerProfile:........." + learnerProfileFactDto);
        // Assigning values from request
        mapper.map(learnerProfileFactDto, learnerProfileFact);
        // Reset Id / Primary key from query parameter
        learnerProfileFact.setPersonid(personid);
        log.info("Update Acceditation:........." + personid);
        return ResponseEntity
                .ok(mapper.map(learnerProfileFactSvc.save(learnerProfileFact),
                        LearnerProfileDto.class));

    }
    /**
     *
     * @param personid
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/learnerprofilefact/{id}")
    public ResponseEntity<HttpStatus> deleteCourseAccreditation(
            @PathVariable(value = "id") final Long personid) {
        try {
            log.info("Deleting  LearnerProfile:........." + personid);
            learnerProfileFactSvc.delete(personid);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error occurred when deleting learner profile fact: ", e);
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
