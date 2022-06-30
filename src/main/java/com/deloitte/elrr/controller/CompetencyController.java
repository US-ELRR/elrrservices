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

import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.CompetencySvc;

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
public class CompetencyController {
    /**
     *
     */
    @Autowired
    private CompetencySvc competencySvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param competencyid
     * @return ResponseEntity<List<CompetencyDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/competency")
    public ResponseEntity<List<CompetencyDto>> getAllCompetencys(
        @RequestParam(value = "id", required = false) final Long competencyid)
            throws ResourceNotFoundException {
        try {
            List<CompetencyDto> competencyList = new ArrayList<>();
            if (competencyid == null) {
                Iterable<Competency> competencys = competencySvc.findAll();

                for (Competency competency : competencys) {
                    CompetencyDto competencyDto = mapper.map(competency,
                            CompetencyDto.class);
                    competencyList.add(competencyDto);
                }
            } else {
                Competency competency = competencySvc.get(competencyid)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Competency not found for this id :: "
                                        + competencyid));
                CompetencyDto competencyDto = mapper.map(competency,
                        CompetencyDto.class);
                competencyList.add(competencyDto);

            }

            if (competencyList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(competencyList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *
     * @param competencyid
     * @return esponseEntity<CompetencyDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/competency/{id}")
    public ResponseEntity<CompetencyDto> getCompetencyById(
            @PathVariable(value = "id") final Long competencyid)
            throws ResourceNotFoundException {
        Competency competency = competencySvc.get(competencyid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Competency not found for this id :: " + competencyid));
        CompetencyDto competencyDto = mapper.map(competency,
                CompetencyDto.class);
        return ResponseEntity.ok().body(competencyDto);
    }
    /**
     *
     * @param competencyDto
     * @return ResponseEntity<CompetencyDto>
     */
    @PostMapping("/competency")
    public ResponseEntity<CompetencyDto> createCompetency(
            @Valid @RequestBody final CompetencyDto competencyDto) {
        log.info("create Competency:........." + competencyDto);
        Competency competency = mapper.map(competencyDto, Competency.class);
        log.info("create Competency:........." + competency);
        mapper.map(competencySvc.save(competency), CompetencyDto.class);
        return new ResponseEntity<>(competencyDto, HttpStatus.CREATED);
    }
    /**
     *
     * @param competencyid
     * @param competencyDto
     * @return ResponseEntity<CompetencyDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/competency/{id}")
    public ResponseEntity<CompetencyDto> updateCompetency(
            @PathVariable(value = "id") final long competencyid,
            @Valid @RequestBody final CompetencyDto competencyDto)
            throws ResourceNotFoundException {
        Competency competency = competencySvc.get(competencyid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Competency not found for this id to update :: "
                                + competencyid));
        log.info("Update Competency:........." + competencyDto);
        // Assigning values from request
        mapper.map(competencyDto, competency);
        // Reset Id / Primary key from query parameter
        competency.setCompetencyid(competencyid);
        log.info("Update Competency:........." + competency);
        return ResponseEntity.ok(mapper.map(competencySvc.save(competency),
                CompetencyDto.class));

    }
    /**
     *
     * @param competencyid
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/competency/{id}")
    public ResponseEntity<HttpStatus> deleteCompetency(
            @PathVariable(value = "id") final Long competencyid) {
        try {
            log.info("Deleting  Competency:........." + competencyid);
            competencySvc.delete(competencyid);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
