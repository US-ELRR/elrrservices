package com.deloitte.elrr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import com.deloitte.elrr.dto.ImportsDTO;
import com.deloitte.elrr.svc.ImportsCreatorSvc;

@CrossOrigin(origins =  {"http://localhost:3001", "http://localhost:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class ImportsController {
	
	@Autowired
	ImportsCreatorSvc svc;
	
	@GetMapping("/getImports")
	public  ImportsDTO  getImports(@RequestParam(value = "name", required = true) String importsName) {
		
		return svc.getImports(importsName);
	}
	
	@GetMapping("/getAllImports")
	public List<ImportsDTO> getAllImports() {
		
		return svc.getAllImports();
	}

}
