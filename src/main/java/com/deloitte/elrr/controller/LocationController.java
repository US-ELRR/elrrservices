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
import org.springframework.data.domain.AbstractAggregateRoot;
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

import com.deloitte.elrr.dto.LocationDto;
import com.deloitte.elrr.entity.Location;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.LocationSvc;
import com.deloitte.elrr.jpa.svc.LocationSvc;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class LocationController {
    /**
     *
     */
    @Autowired
    private LocationSvc locationSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param locationId
     * @return ResponseEntity<List<LocationDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/location")
    public ResponseEntity<List<LocationDto>> getAllLocations(
            @RequestParam(value = "id", required = false) final UUID locationId) throws ResourceNotFoundException {
        try {
            log.debug("Get Location id:........." + locationId);
            List<LocationDto> locationList = new ArrayList<>();
            if (locationId == null) {
                locationSvc.findAll().forEach(loc -> locationList.add(
                        mapper.map(loc, LocationDto.class)));
            } else {
                Location location = locationSvc.get(locationId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Location not found for this id :: "
                                        + locationId));
                LocationDto locationDto = mapper.map(location, LocationDto.class);
                locationList.add(locationDto);
            }

            if (locationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(locationList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param locationId
     * @return ResponseEntity<LocationDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/location/{id}")
    public ResponseEntity<LocationDto> getLocationById(
            @PathVariable(value = "id") final UUID locationId)
            throws ResourceNotFoundException {
        log.debug("Get Location id:........." + locationId);
        Location location = locationSvc.get(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Location not found for this id :: "
                                + locationId));
        LocationDto locationDto = mapper.map(location,
                LocationDto.class);
        return ResponseEntity.ok().body(locationDto);
    }

    /**
     *
     * @param locationDto
     * @return ResponseEntity<LocationDto>
     */
    @PostMapping("/location")
    public ResponseEntity<LocationDto> createLocation(
            @Valid @RequestBody final LocationDto locationDto) {
        Location org = mapper.map(locationDto, Location.class);
        LocationDto response = mapper.map(locationSvc.save(org), LocationDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param locationId
     * @param locationDto
     * @return ResponseEntity<LocationDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/location/{id}")
    public ResponseEntity<LocationDto> updateLocation(
            @PathVariable(value = "id") final UUID locationId,
            @Valid @RequestBody final LocationDto locationDto)
            throws ResourceNotFoundException {
        log.info("Updating  Location:.........");
        log.info("Updating Location id:........." + locationId);
        Location location = locationSvc.get(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Location not found for this id to update :: "
                                + locationId));
        log.info("Update Location:........." + locationDto);
        // Assigning values from request
        mapper.map(locationDto, location);
        // Reset Id / Primary key from query parameter
        location.setId(locationId);
        log.info("Update Location:........." + location);
        return ResponseEntity.ok(mapper.map(locationSvc.save(location),
                LocationDto.class));

    }

    /**
     *
     * @param locationId
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/location/{id}")
    public ResponseEntity<HttpStatus> deleteLocation(
            @PathVariable(value = "id") final UUID locationId) {
        try {
            log.info("Deleting  Location:.........");
            log.info("Deleting Location id:........." + locationId);
            locationSvc.delete(locationId);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
