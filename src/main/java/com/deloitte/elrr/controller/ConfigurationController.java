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

import com.deloitte.elrr.dto.ConfigurationDto;
import com.deloitte.elrr.entity.Configuration;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.ConfigurationSvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class ConfigurationController {

	@Autowired
	private ConfigurationSvc configurationSvc;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/configuration")
	public ResponseEntity<List<ConfigurationDto>> getAllConfigurations(@RequestParam(value = "id", required = false) Long configurationid)
			throws ResourceNotFoundException {
		try {
			List<ConfigurationDto> configurationList = new ArrayList<>();
			if (configurationid == null) {
				Iterable<Configuration> configurations = configurationSvc.findAll();

				for (Configuration configuration : configurations) {
					ConfigurationDto configurationDto = mapper.map(configuration, ConfigurationDto.class);
					configurationList.add(configurationDto);
				}
			} else {
				Configuration configuration = configurationSvc.get(configurationid).orElseThrow(
						() -> new ResourceNotFoundException("Configuration not found for this id :: " + configurationid));
				ConfigurationDto configurationDto = mapper.map(configuration, ConfigurationDto.class);
				configurationList.add(configurationDto);

			}

			if (configurationList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return ResponseEntity.ok(configurationList);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/configuration/{id}")
	public ResponseEntity<ConfigurationDto> getConfigurationById(@PathVariable(value = "id") Long configurationid)
			throws ResourceNotFoundException {
		Configuration configuration = configurationSvc.get(configurationid)
				.orElseThrow(() -> new ResourceNotFoundException("Configuration not found for this id :: " + configurationid));
		ConfigurationDto configurationDto = mapper.map(configuration, ConfigurationDto.class);
		return ResponseEntity.ok().body(configurationDto);
	}

	@PostMapping("/configuration")
	public ResponseEntity<ConfigurationDto> createConfiguration(@Valid @RequestBody ConfigurationDto configurationDto) {
		log.info("create Configuration:........." + configurationDto);
		Configuration configuration = mapper.map(configurationDto, Configuration.class);
		log.info("create Configuration:........." + configuration);
		mapper.map(configurationSvc.save(configuration), ConfigurationDto.class);
		return new ResponseEntity<>(configurationDto, HttpStatus.CREATED);
	}

	@PutMapping("/configuration/{id}")
	public ResponseEntity<ConfigurationDto> updateConfiguration(@PathVariable(value = "id") long configurationid,
			@Valid @RequestBody ConfigurationDto configurationDto) throws ResourceNotFoundException {
		Configuration configuration = configurationSvc.get(configurationid).orElseThrow(
				() -> new ResourceNotFoundException("Configuration not found for this id to update :: " + configurationid));
		log.info("Update Configuration:........." + configurationDto);
		// Assigning values from request
		mapper.map(configurationDto, configuration);
		//Reset Id / Primary key from query parameter
		configuration.setConfigurationid(configurationid);
		log.info("Update Configuration:........." + configuration);
		return ResponseEntity.ok(mapper.map(configurationSvc.save(configuration), ConfigurationDto.class));

	}

	@DeleteMapping("/configuration/{id}")
	public ResponseEntity<HttpStatus> deleteConfiguration(@PathVariable(value = "id") Long configurationid) {
		try {
			log.info("Deleting  Configuration:........." + configurationid);
			configurationSvc.delete(configurationid);
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Don't want to expose this method as it will allow to delete all the configuration
	// records
	/*
	 * @DeleteMapping("/configuration") public ResponseEntity<HttpStatus>
	 * deleteAllpersons() { try { log.info("Deleting  All Configuration:.........");
	 * configurationSvc.deleteAll(); return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
