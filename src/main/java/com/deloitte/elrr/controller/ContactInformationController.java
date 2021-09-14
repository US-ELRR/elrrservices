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

import com.deloitte.elrr.dto.ContactInformationDto;
import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.ContactInformationSvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {"http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001", "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class ContactInformationController {

	@Autowired
	private ContactInformationSvc contactInformationSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/contactinformation")
	public ResponseEntity<List<ContactInformationDto>> getAllCompetencys(@RequestParam(value = "id", required = false) Long contactInformationid)
			throws ResourceNotFoundException {
		try {
			List<ContactInformationDto> contactInformationList = new ArrayList<>();
			if (contactInformationid == null) {
				Iterable<ContactInformation> competencys = contactInformationSvc.findAll();

				for (ContactInformation contactInformation : competencys) {
					ContactInformationDto contactInformationDto = mapper.map(contactInformation, ContactInformationDto.class);
					contactInformationList.add(contactInformationDto);
				}
			} else {
				ContactInformation contactInformation = contactInformationSvc.get(contactInformationid).orElseThrow(
						() -> new ResourceNotFoundException("ContactInformation not found for this id :: " + contactInformationid));
				ContactInformationDto contactInformationDto = mapper.map(contactInformation, ContactInformationDto.class);
				contactInformationList.add(contactInformationDto);

			}

			if (contactInformationList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(contactInformationList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/contactnnformation/{id}")
	public ResponseEntity<ContactInformationDto> getCompetencyById(@PathVariable(value = "id") Long contactInformationid)
			throws ResourceNotFoundException {
		ContactInformation contactInformation = contactInformationSvc.get(contactInformationid)
				.orElseThrow(() -> new ResourceNotFoundException("ContactInformation not found for this id :: " + contactInformationid));
		ContactInformationDto contactInformationDto = mapper.map(contactInformation, ContactInformationDto.class);
		return ResponseEntity.ok().body(contactInformationDto);
	}

	@PostMapping("/contactinformation")
	public ResponseEntity<ContactInformationDto> createCompetency(@Valid @RequestBody ContactInformationDto contactInformationDto) {
		log.info("create ContactInformation:........." + contactInformationDto);
		ContactInformation contactInformation = mapper.map(contactInformationDto, ContactInformation.class);
		log.info("create ContactInformation:........." + contactInformation);
		mapper.map(contactInformationSvc.save(contactInformation), ContactInformationDto.class);
		return new ResponseEntity<>(contactInformationDto, HttpStatus.CREATED);
	}

	@PutMapping("/contactinformation/{id}")
	public ResponseEntity<ContactInformationDto> updateCompetency(@PathVariable(value = "id") long contactInformationid,
			@Valid @RequestBody ContactInformationDto contactInformationDto) throws ResourceNotFoundException {
		ContactInformation contactInformation = contactInformationSvc.get(contactInformationid).orElseThrow(
				() -> new ResourceNotFoundException("ContactInformation not found for this id to update :: " + contactInformationid));
		log.info("Update ContactInformation:........." + contactInformationDto);
		// Assigning values from request
		mapper.map(contactInformationDto, contactInformation);
		//Reset Id / Primary key from query parameter
		contactInformation.setContactinformationid(contactInformationid);
		log.info("Update ContactInformation:........." + contactInformation);
		return ResponseEntity.ok(mapper.map(contactInformationSvc.save(contactInformation), ContactInformationDto.class));

	}

	@DeleteMapping("/contactinformation/{id}")
	public ResponseEntity<HttpStatus> deleteCompetency(@PathVariable(value = "id") Long contactInformationid) {
		try {
			log.info("Deleting  ContactInformation:........." + contactInformationid);
			contactInformationSvc.delete(contactInformationid);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the contactInformation
	// records
	/*
	 * @DeleteMapping("/contactinformation") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try { log.info("Deleting  All ContactInformation:.........");
	 * contactInformationSvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
