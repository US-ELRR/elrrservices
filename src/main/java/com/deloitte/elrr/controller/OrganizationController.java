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

import com.deloitte.elrr.dto.OrganizationDto;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.OrganizationSvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class OrganizationController {

	@Autowired
	private OrganizationSvc organizationSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/organization")
	public ResponseEntity<List<OrganizationDto>> getAllOrganizations(@RequestParam(value = "id", required = false) Long organizationid)
			throws ResourceNotFoundException {
		try {
			List<OrganizationDto> organizationList = new ArrayList<>();
			if (organizationid == null) {
				Iterable<Organization> organizations = organizationSvc.findAll();

				for (Organization organization : organizations) {
					OrganizationDto organizationDto = mapper.map(organization, OrganizationDto.class);
					organizationList.add(organizationDto);
				}
			} else {
				Organization organization = organizationSvc.get(organizationid).orElseThrow(
						() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationid));
				OrganizationDto organizationDto = mapper.map(organization, OrganizationDto.class);
				organizationList.add(organizationDto);

			}

			if (organizationList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(organizationList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/organization/{id}")
	public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable(value = "id") Long organizationid)
			throws ResourceNotFoundException {
		Organization organization = organizationSvc.get(organizationid)
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationid));
		OrganizationDto organizationDto = mapper.map(organization, OrganizationDto.class);
		return ResponseEntity.ok().body(organizationDto);
	}

	@PostMapping("/organization")
	public ResponseEntity<OrganizationDto> createOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
		log.info("create Organization:........." + organizationDto);
		Organization organization = mapper.map(organizationDto, Organization.class);
		log.info("create Organization:........." + organization);
		mapper.map(organizationSvc.save(organization), OrganizationDto.class);
		return new ResponseEntity<>(organizationDto, HttpStatus.CREATED);
	}

	@PutMapping("/organization/{id}")
	public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable(value = "id") long organizationid,
			@Valid @RequestBody OrganizationDto organizationDto) throws ResourceNotFoundException {
		Organization organization = organizationSvc.get(organizationid).orElseThrow(
				() -> new ResourceNotFoundException("Organization not found for this id to update :: " + organizationid));
		log.info("Update Organization:........." + organizationDto);
		// Assigning values from request
		mapper.map(organizationDto, organization);
		//Reset Id / Primary key from query parameter
		organization.setOrganizationid(organizationid);
		log.info("Update Organization:........." + organization);
		return ResponseEntity.ok(mapper.map(organizationSvc.save(organization), OrganizationDto.class));

	}

	@DeleteMapping("/organization/{id}")
	public ResponseEntity<HttpStatus> deleteOrganization(@PathVariable(value = "id") Long organizationid) {
		try {
			log.info("Deleting  Organization:........." + organizationid);
			organizationSvc.delete(organizationid);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the organization
	// records
	/*
	 * @DeleteMapping("/organization") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try { log.info("Deleting  All Organization:.........");
	 * organizationSvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
