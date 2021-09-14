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
@CrossOrigin(origins = {"http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001", "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class CompetencyController {

	@Autowired
	private CompetencySvc competencySvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/competency")
	public ResponseEntity<List<CompetencyDto>> getAllCompetencys(@RequestParam(value = "id", required = false) Long competencyid)
			throws ResourceNotFoundException {
		try {
			List<CompetencyDto> competencyList = new ArrayList<>();
			if (competencyid == null) {
				Iterable<Competency> competencys = competencySvc.findAll();

				for (Competency competency : competencys) {
					CompetencyDto competencyDto = mapper.map(competency, CompetencyDto.class);
					competencyList.add(competencyDto);
				}
			} else {
				Competency competency = competencySvc.get(competencyid).orElseThrow(
						() -> new ResourceNotFoundException("Competency not found for this id :: " + competencyid));
				CompetencyDto competencyDto = mapper.map(competency, CompetencyDto.class);
				competencyList.add(competencyDto);

			}

			if (competencyList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(competencyList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/competency/{id}")
	public ResponseEntity<CompetencyDto> getCompetencyById(@PathVariable(value = "id") Long competencyid)
			throws ResourceNotFoundException {
		Competency competency = competencySvc.get(competencyid)
				.orElseThrow(() -> new ResourceNotFoundException("Competency not found for this id :: " + competencyid));
		CompetencyDto competencyDto = mapper.map(competency, CompetencyDto.class);
		return ResponseEntity.ok().body(competencyDto);
	}

	@PostMapping("/competency")
	public ResponseEntity<CompetencyDto> createCompetency(@Valid @RequestBody CompetencyDto competencyDto) {
		log.info("create Competency:........." + competencyDto);
		Competency competency = mapper.map(competencyDto, Competency.class);
		log.info("create Competency:........." + competency);
		mapper.map(competencySvc.save(competency), CompetencyDto.class);
		return new ResponseEntity<>(competencyDto, HttpStatus.CREATED);
	}

	@PutMapping("/competency/{id}")
	public ResponseEntity<CompetencyDto> updateCompetency(@PathVariable(value = "id") long competencyid,
			@Valid @RequestBody CompetencyDto competencyDto) throws ResourceNotFoundException {
		Competency competency = competencySvc.get(competencyid).orElseThrow(
				() -> new ResourceNotFoundException("Competency not found for this id to update :: " + competencyid));
		log.info("Update Competency:........." + competencyDto);
		// Assigning values from request
		mapper.map(competencyDto, competency);
		//Reset Id / Primary key from query parameter
		competency.setCompetencyid(competencyid);
		log.info("Update Competency:........." + competency);
		return ResponseEntity.ok(mapper.map(competencySvc.save(competency), CompetencyDto.class));

	}

	@DeleteMapping("/competency/{id}")
	public ResponseEntity<HttpStatus> deleteCompetency(@PathVariable(value = "id") Long competencyid) {
		try {
			log.info("Deleting  Competency:........." + competencyid);
			competencySvc.delete(competencyid);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the competency
	// records
	/*
	 * @DeleteMapping("/competency") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try { log.info("Deleting  All Competency:.........");
	 * competencySvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
