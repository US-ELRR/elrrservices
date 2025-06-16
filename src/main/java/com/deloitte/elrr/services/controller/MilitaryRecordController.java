package com.deloitte.elrr.services.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.deloitte.elrr.entity.MilitaryRecord;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.jpa.svc.MilitaryRecordSvc;
import com.deloitte.elrr.services.dto.MilitaryRecordDto;
import com.deloitte.elrr.services.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class MilitaryRecordController {

    @Autowired
    private MilitaryRecordSvc militaryRecordSvc;

    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param militaryRecordId
     * @return ResponseEntity<List<MilitaryRecordDto>>
     * @throws ResourceNotFoundException
     */
    @PreAuthorize("hasPermission('militaryrecord', 'READ')")
    @GetMapping("/militaryrecord")
    public ResponseEntity<List<MilitaryRecordDto>> getAllMilitaryRecords(
            @RequestParam(value = "id", required = false)
            final UUID militaryRecordId) throws ResourceNotFoundException {
        try {
            log.debug("Get MilitaryRecord id:........." + militaryRecordId);
            List<MilitaryRecordDto> militaryRecordList = new ArrayList<>();
            if (militaryRecordId == null) {
                militaryRecordSvc.findAll()
                        .forEach(loc -> militaryRecordList.add(
                                mapper.map(loc, MilitaryRecordDto.class)));
            } else {
                MilitaryRecord militaryRecord = militaryRecordSvc
                        .get(militaryRecordId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "MilitaryRecord not found for this id :: "
                                        + militaryRecordId));
                MilitaryRecordDto militaryRecordDto = mapper.map(militaryRecord,
                        MilitaryRecordDto.class);
                militaryRecordList.add(militaryRecordDto);
            }

            if (militaryRecordList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(militaryRecordList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param militaryRecordId
     * @return ResponseEntity<MilitaryRecordDto>
     * @throws ResourceNotFoundException
     */
    @PreAuthorize("hasPermission('militaryrecord', 'READ')")
    @GetMapping("/militaryrecord/{id}")
    public ResponseEntity<MilitaryRecordDto> getMilitaryRecordById(
            @PathVariable(value = "id") final UUID militaryRecordId)
            throws ResourceNotFoundException {
        log.debug("Get MilitaryRecord id:........." + militaryRecordId);
        MilitaryRecord militaryRecord = militaryRecordSvc.get(militaryRecordId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MilitaryRecord not found for this id :: "
                                + militaryRecordId));
        MilitaryRecordDto militaryRecordDto = mapper.map(militaryRecord,
                MilitaryRecordDto.class);
        return ResponseEntity.ok().body(militaryRecordDto);
    }

    /**
     *
     * @param militaryRecordId
     * @param militaryRecordDto
     * @return ResponseEntity<MilitaryRecordDto>
     * @throws ResourceNotFoundException
     */
    @PreAuthorize(
        "hasPermission('militaryrecord', 'UPDATE')")
    @PutMapping("/militaryrecord/{id}")
    public ResponseEntity<MilitaryRecordDto> updateMilitaryRecord(
            @PathVariable(value = "id") final UUID militaryRecordId,
            @Valid @RequestBody final MilitaryRecordDto militaryRecordDto)
            throws ResourceNotFoundException {
        log.info("Updating  MilitaryRecord:.........");
        log.info("Updating MilitaryRecord id:........." + militaryRecordId);
        MilitaryRecord militaryRecord = militaryRecordSvc.get(militaryRecordId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MilitaryRecord not found for this id to update :: "
                                + militaryRecordId));
        Person person = militaryRecord.getPerson();

        mapper.map(militaryRecordDto, militaryRecord);

        militaryRecord.setId(militaryRecordId);
        militaryRecord.setPerson(person);

        log.info("Update MilitaryRecord:........." + militaryRecordId);
        return ResponseEntity
                .ok(mapper.map(militaryRecordSvc.save(militaryRecord),
                        MilitaryRecordDto.class));

    }

    /**
     *
     * @param militaryRecordId
     * @return ResponseEntity<HttpStatus>
     * @throws ResourceNotFoundException
     */
    @PreAuthorize(
        "hasPermission('militaryrecord', 'DELETE')")
    @DeleteMapping("/militaryrecord/{id}")
    public ResponseEntity<HttpStatus> deleteMilitaryRecord(
            @PathVariable(value = "id") final UUID militaryRecordId)
            throws ResourceNotFoundException {
        log.info("Deleting  MilitaryRecord:.........");
        log.info("Deleting MilitaryRecord id:........." + militaryRecordId);
        militaryRecordSvc.get(militaryRecordId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MilitaryRecord not found for this id to delete :: "
                                + militaryRecordId));
        militaryRecordSvc.delete(militaryRecordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
