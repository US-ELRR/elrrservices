package com.deloitte.elrr.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.EmailValidator;
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

@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class HomeController {
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;
    /**
     *
     */
    @Autowired
    private LearnerCreatorSvc learnerCreatorSvc;
    /**
     *
     * @param userName
     * @param password
     * @return Learner
     */
    @RequestMapping(value = "/learner", method = RequestMethod.GET, produces = {
            "application/json" })
    public Learner getLearner(@RequestParam(required = false)
        final String userName, @RequestParam(required = false)
        final String password) {

        if (!EmailValidator.getInstance().isValid(userName))
        {
            return null;
        }

        Learner learner = null;
        learner = learnerCreatorSvc.learnerCreator(userName);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        log.info(dtf.format(now));
        log.info("" + learner);
        return learner;
    }
}
