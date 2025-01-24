/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

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

import com.deloitte.elrr.dto.EmailDto;
import com.deloitte.elrr.entity.Email;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.EmailSvc;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class EmailController {
    /**
     *
     */
    @Autowired
    private EmailSvc emailSvc;
    /**
     *
     */
    @Autowired
    private ModelMapper mapper;

    /**
     *
     * @param emailId
     * @return ResponseEntity<List<EmailDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/email")
    public ResponseEntity<List<EmailDto>> getAllEmails(
            @RequestParam(value = "id", required = false) final UUID emailId) throws ResourceNotFoundException {
        try {
            log.debug("Get Email id:........." + emailId);
            List<EmailDto> emailList = new ArrayList<>();
            if (emailId == null) {
                emailSvc.findAll().forEach(email -> emailList.add(
                        mapper.map(email, EmailDto.class)));
            } else {
                Email email = emailSvc.get(emailId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Email not found for this id :: "
                                        + emailId));
                EmailDto emailDto = mapper.map(email, EmailDto.class);
                emailList.add(emailDto);
            }

            if (emailList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(emailList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param emailId
     * @return ResponseEntity<EmailDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/email/{id}")
    public ResponseEntity<EmailDto> getEmailById(
            @PathVariable(value = "id") final UUID emailId)
            throws ResourceNotFoundException {
        log.debug("Get Email id:........." + emailId);
        Email email = emailSvc.get(emailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Email not found for this id :: "
                                + emailId));
        EmailDto emailDto = mapper.map(email,
                EmailDto.class);
        return ResponseEntity.ok().body(emailDto);
    }

    /**
     *
     * @param emailDto
     * @return ResponseEntity<EmailDto>
     */
    @PostMapping("/email")
    public ResponseEntity<EmailDto> createEmail(
            @Valid @RequestBody final EmailDto emailDto) {
        Email email = mapper.map(emailDto, Email.class);
        EmailDto response = mapper.map(emailSvc.save(email), EmailDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param emailId
     * @param emailDto
     * @return ResponseEntity<EmailDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/email/{id}")
    public ResponseEntity<EmailDto> updateEmail(
            @PathVariable(value = "id") final UUID emailId,
            @Valid @RequestBody final EmailDto emailDto)
            throws ResourceNotFoundException {
        log.info("Updating  Email:.........");
        log.info("Updating Email id:........." + emailId);
        Email email = emailSvc.get(emailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Email not found for this id to update :: "
                                + emailId));
        log.info("Update Email:........." + emailDto);
        // Assigning values from request
        mapper.map(emailDto, email);
        // Reset Id / Primary key from query parameter
        email.setId(emailId);
        log.info("Update Email:........." + email);
        return ResponseEntity.ok(mapper.map(emailSvc.save(email),
                EmailDto.class));

    }

    /**
     *
     * @param emailId
     * @return ResponseEntity<HttpStatus>
          * @throws ResourceNotFoundException 
          */
          @DeleteMapping("/email/{id}")
          public ResponseEntity<HttpStatus> deleteFacility(
                  @PathVariable(value = "id") final UUID emailId) 
                  throws ResourceNotFoundException {
         log.info("Deleting  Email:.........");
         log.info("Deleting Email id:........." + emailId);
         emailSvc.get(emailId).orElseThrow(() -> new ResourceNotFoundException(
                     "Email not found for this id to delete :: " + emailId));
         emailSvc.delete(emailId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

}
