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

import com.deloitte.elrr.dto.AccreditationDto;
import com.deloitte.elrr.entity.Accreditation;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.AccreditationSvc;

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
public class AccreditationController {
    /**
     *
     */
    @Autowired
    private AccreditationSvc accreditationSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param accreditationid
     * @return ResponseEntity<>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/accreditation")
    public ResponseEntity<List<AccreditationDto>> getAllAccreditations(
     @RequestParam(value = "id", required = false) final Long accreditationid)
        throws ResourceNotFoundException {

        try {
            List<AccreditationDto> accreditationList = new ArrayList<>();
            if (accreditationid == null) {
                Iterable<Accreditation> accreditations = accreditationSvc
                        .findAll();
                for (Accreditation accreditation : accreditations) {
                    AccreditationDto accreditationDto = mapper
                            .map(accreditation, AccreditationDto.class);
                    accreditationList.add(accreditationDto);
                }
            } else {
                Accreditation accreditation = accreditationSvc
                        .get(accreditationid)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Accreditation not found for this id :: "
                                        + accreditationid));
                AccreditationDto accreditationDto = mapper.map(accreditation,
                        AccreditationDto.class);
                accreditationList.add(accreditationDto);

            }

            if (accreditationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(accreditationList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param accreditationid
     * @return ResponseEntity<AccreditationDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/accreditation/{id}")
    public ResponseEntity<AccreditationDto> getAccreditationById(
            @PathVariable(value = "id") final Long accreditationid)
            throws ResourceNotFoundException {
        Accreditation acceditation = accreditationSvc.get(accreditationid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Accreditation not found for this id :: "
                                + accreditationid));
        AccreditationDto accreditationDto = mapper.map(acceditation,
                AccreditationDto.class);
        return ResponseEntity.ok().body(accreditationDto);
    }
    /**
     *
     * @param accreditationDto
     * @return ResponseEntity<AccreditationDto>
     */
    @PostMapping("/accreditation")
    public ResponseEntity<AccreditationDto> createAccreditation(
            @Valid @RequestBody final AccreditationDto accreditationDto) {

        Accreditation accreditation = mapper.map(accreditationDto,
                Accreditation.class);
        mapper.map(accreditationSvc.save(accreditation),
                AccreditationDto.class);
        return new ResponseEntity<>(accreditationDto, HttpStatus.CREATED);
    }
    /**
     *
     * @param accreditationid
     * @param accreditationDto
     * @return ResponseEntity<AccreditationDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/accreditation/{id}")
    public ResponseEntity<AccreditationDto> updateAccreditation(
            @PathVariable(value = "id") final long accreditationid,
            @Valid @RequestBody final AccreditationDto accreditationDto)
            throws ResourceNotFoundException {
        Accreditation accreditation = accreditationSvc.get(accreditationid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Accreditation not found for this id to update :: "
                                + accreditationid));
        log.info("accreditationDto:........." + accreditationDto);
        log.info("accreditationid:........." + accreditationid);
        log.info("Update Accreditation:.........");
        // Assigning values from request
        mapper.map(accreditationDto, accreditation);
        // Reset Id / Primary key from query parameter
        accreditation.setAccreditationid(accreditationid);
        log.info("Update Acceditation:........." + accreditation);
        return ResponseEntity.ok(mapper.map(
                accreditationSvc.save(accreditation), AccreditationDto.class));

    }
    /**
     *
     * @param accreditationid
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/accreditation/{id}")
    public ResponseEntity<HttpStatus> deleteAccreditation(
            @PathVariable(value = "id") final Long accreditationid) {
        try {
            log.info("Deleting  Accreditation:........." + accreditationid);
            accreditationSvc.delete(accreditationid);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
