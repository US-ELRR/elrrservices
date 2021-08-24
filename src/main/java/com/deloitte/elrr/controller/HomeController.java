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

import com.deloitte.elrr.dto.LearnerDTO;
import com.deloitte.elrr.dto.LearnerSnapshotDTO;
import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.svc.LearnerCreatorSvc;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@Slf4j
public class HomeController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	private LearnerCreatorSvc learnerCreatorSvc;

	@RequestMapping(value = "/learner", method = RequestMethod.GET, produces = {"application/json"})
	public LearnerDTO getLearner(@RequestParam(required = false) String userName, @RequestParam(required = false) String password) {

		log.info(userName +"_"+ password);

		Learner learner = new Learner();
		LearnerDTO learnerDTO = learnerCreatorSvc.learnerCreator(userName);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		log.info(dtf.format(now));

		return learnerDTO;
	}
	
	@RequestMapping(value = "/sysadmin", method = RequestMethod.GET, produces = {"application/json"})
	public LearnerSnapshotDTO getLearnerSnapshot() {
	
		LearnerSnapshotDTO snapshotDTO = learnerCreatorSvc.getSnapshot();
		return snapshotDTO;
	}
}
