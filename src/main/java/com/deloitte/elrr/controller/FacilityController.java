/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import com.deloitte.elrr.dto.FacilityDto;
import com.deloitte.elrr.entity.Facility;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.FacilitySvc;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class FacilityController {
    
    @Autowired
    private FacilitySvc facilitySvc;
    
    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param facilityId
     * @return ResponseEntity<List<FacilityDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/facility")
    public ResponseEntity<List<FacilityDto>> getAllFacilitys(
            @RequestParam(value = "id", required = false) final UUID facilityId) throws ResourceNotFoundException {
        try {
            log.debug("Get Facility id:........." + facilityId);
            List<FacilityDto> facilityList = new ArrayList<>();
            if (facilityId == null) {
                facilitySvc.findAll().forEach(fac -> facilityList.add(
                        mapper.map(fac, FacilityDto.class)));
            } else {
                Facility facility = facilitySvc.get(facilityId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Facility not found for this id :: "
                                        + facilityId));
                FacilityDto facilityDto = mapper.map(facility, FacilityDto.class);
                facilityList.add(facilityDto);
            }

            if (facilityList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(facilityList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param facilityId
     * @return ResponseEntity<FacilityDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/facility/{id}")
    public ResponseEntity<FacilityDto> getFacilityById(
            @PathVariable(value = "id") final UUID facilityId)
            throws ResourceNotFoundException {
        log.debug("Get Facility id:........." + facilityId);
        Facility facility = facilitySvc.get(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Facility not found for this id :: "
                                + facilityId));
        FacilityDto facilityDto = mapper.map(facility,
                FacilityDto.class);
        return ResponseEntity.ok().body(facilityDto);
    }

    /**
     *
     * @param facilityDto
     * @return ResponseEntity<FacilityDto>
     */
    @PostMapping("/facility")
    public ResponseEntity<FacilityDto> createFacility(
            @Valid @RequestBody final FacilityDto facilityDto) {
        Facility facility = mapper.map(facilityDto, Facility.class);
        FacilityDto response = mapper.map(facilitySvc.save(facility), FacilityDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param facilityId
     * @param facilityDto
     * @return ResponseEntity<FacilityDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/facility/{id}")
    public ResponseEntity<FacilityDto> updateFacility(
            @PathVariable(value = "id") final UUID facilityId,
            @Valid @RequestBody final FacilityDto facilityDto)
            throws ResourceNotFoundException {
        log.info("Updating  Facility:.........");
        log.info("Updating Facility id:........." + facilityId);
        Facility facility = facilitySvc.get(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Facility not found for this id to update :: "
                                + facilityId));
        log.info("Update Facility:........." + facilityDto);
        // Assigning values from request
        mapper.map(facilityDto, facility);
        // Reset Id / Primary key from query parameter
        facility.setId(facilityId);
        log.info("Update Facility:........." + facility);
        return ResponseEntity.ok(mapper.map(facilitySvc.save(facility),
                FacilityDto.class));

    }

    /**
     *
     * @param facilityId
     * @return ResponseEntity<HttpStatus>
          * @throws ResourceNotFoundException 
          */
         @DeleteMapping("/facility/{id}")
         public ResponseEntity<HttpStatus> deleteFacility(
                 @PathVariable(value = "id") final UUID facilityId) 
                 throws ResourceNotFoundException {
        log.info("Deleting  Facility:.........");
        log.info("Deleting Facility id:........." + facilityId);
        facilitySvc.get(facilityId).orElseThrow(() -> new ResourceNotFoundException(
                    "Facility not found for this id to delete :: " + facilityId));
        facilitySvc.delete(facilityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
