package com.deloitte.elrr.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.svc.LearnerCreatorSvc;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins =  {"http://localhost:3001", "http://localhost:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class HomeController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private LearnerCreatorSvc learnerCreatorSvc;
	
	@RequestMapping(value = "/learner", method = RequestMethod.POST, produces = {"application/json"})
	public Learner getLearner(@RequestParam(required = false) String userName, @RequestParam(required = false) String password) {
		log.info(userName +"_"+ password);
		
		Learner learner = new Learner();
		learner = learnerCreatorSvc.learnerCreator(userName);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		log.info(dtf.format(now));  
		
		return learner;
	}
}
