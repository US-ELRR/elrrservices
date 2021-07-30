package com.deloitte.elrr.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.elrr.dto.ElrrException;
import com.deloitte.elrr.dto.FailureType;
import com.deloitte.elrr.dto.LearnerDTO;
import com.deloitte.elrr.dto.LearnersDTO;
import com.deloitte.elrr.svc.LearnerCreatorSvc;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins =  {"http://localhost:3001", "http://localhost:5000"})
@RestController
@RequestMapping("api")
@Slf4j
public class LearnersController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	private LearnerCreatorSvc learnerCreatorSvc;
	@RequestMapping(value = "/learners", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> getLearner(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String endDate, 
			@RequestParam(required = false) String orgName, @RequestParam(required = false) String[] responseEntities,
			@RequestParam(required = false) Integer startRecord) {
		
		log.info("startRecord "+startRecord);
		Date from = getDate(fromDate);
		Date end = null;
		if (endDate == null) {
			end = new Date();
		} else {
			end = getDate(endDate);
		}
		if (startRecord == null) {
 			startRecord=1;
 		}
 		ElrrException exception = validate(from, end, orgName, responseEntities, startRecord);
 		if (exception == null) {
 			LearnersDTO learnerDTO = learnerCreatorSvc.getLearners(from, end, orgName, responseEntities, startRecord);
 			return ResponseEntity.ok(learnerDTO);
 		} else {
 			return ResponseEntity.ok(exception);
 		}
	}


	/*
	 * validate input parameters for not null and format
	 */
	
	private ElrrException validate(Date fromDate, Date endDate, String orgName, String[] responseEntities, Integer startRecord) {

		if (fromDate == null) {
			ElrrException exception = new ElrrException();
			exception.setFailureType(FailureType.Validation);
			exception.setFieldName("fromDate");
			exception.setErrorMessage("Start date should be in YYYY-MM-dd format");
			return exception;
		}
		if (endDate == null) {
			ElrrException exception = new ElrrException();
			exception.setFailureType(FailureType.Validation);
			exception.setFieldName("endDate");
			exception.setErrorMessage("End date should be in YYYY-MM-dd format");
			return exception;
		}

		if (fromDate.after(endDate)) {
			ElrrException exception = new ElrrException();
			exception.setFailureType(FailureType.Validation);
			exception.setFieldName("fromDate");
			exception.setErrorMessage("Start date can not be after end date");
			return exception;
		}
		if (startRecord != null && startRecord <1) {
			log.info("inside if ");
			ElrrException exception = new ElrrException();
			exception.setFailureType(FailureType.Validation);
			exception.setFieldName("startRecord");
			exception.setErrorMessage("startRecord can't be less than 1");
			return exception;
		}

		if (responseEntities != null) {
			log.info("inside responseEntities ");
			if (responseEntities.length > 2) {
				ElrrException exception = new ElrrException();
				exception.setFailureType(FailureType.Validation);
				exception.setFieldName("responseEntities");
				exception.setErrorMessage("responseEntities cannot be more than 2");
				return exception;
			}
			for (String str: responseEntities) {
				log.info("str "+str);
				if (!((str.equals("COURSE" ) || str.equals("COMPETENCY")))) {
					ElrrException exception = new ElrrException();
					exception.setFailureType(FailureType.Validation);
					exception.setFieldName("responseEntities");
					exception.setErrorMessage("responseEntities can be only COURSE or COMPETENCY");
					return exception;
				}
			}
		}
		if (orgName == null) {
			log.info("inside if ");
			ElrrException exception = new ElrrException();
			exception.setFailureType(FailureType.Validation);
			exception.setFieldName("orgName");
			exception.setErrorMessage("Organization Name must be provided");
			return exception;
		}
		
		return null;
	}
	
	private Date getDate(String str) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(str);
					
		} catch(Exception e) {
			log.error("failed for value "+str);
			return null;
		}
	}
}
