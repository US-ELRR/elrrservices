/**
 *
 */
package com.deloitte.elrr.controller;

import java.net.URLDecoder;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.LearnerProfile;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.ContactInformationSvc;
import com.deloitte.elrr.jpa.svc.CourseSvc;
import com.deloitte.elrr.jpa.svc.LearnerProfileSvc;

import gov.adlnet.xapi.client.StatementClient;
import gov.adlnet.xapi.model.Activity;
import gov.adlnet.xapi.model.Actor;
import gov.adlnet.xapi.model.Agent;
import gov.adlnet.xapi.model.Statement;
import gov.adlnet.xapi.model.StatementResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class ELRRStageController {
    /**
     *
     */
    @Value("lrs.url")
    private String lrsURL;
    /**
     *
     */
    @Value("lrs.username")
    private String lrsUsername;
    /**
     *
     */
    @Value("lrs.password")
    private String lrsPassword;
    /**
     *
     */
    @Value("elrr.dataservicesurl")
    private String dataservicesurl;
    /**
     *
     */
    @Autowired
    private CourseSvc courseSvc;
    /**
     *
     */
    @Autowired
    private ContactInformationSvc contactInformationSvc;
    /**
     *
     */
    @Autowired
    private LearnerProfileSvc learnerProfileSvc;

    /**
     *
     */
    public static final int LIMIT = 300;
    /**
     *
     * @param mbox
     * @return ResponseEntity<String>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/elrrstagedata")
    public ResponseEntity<String> localData(
            @RequestParam(value = "mbox",
            defaultValue = "mailto:c.cooper@yahoo.com") final String mbox)
            throws ResourceNotFoundException {
        String response = "LRS statement data Loaded"
                + " successfully to ELRR Staging Database";
        String dateQuery = "2021-01-02T00:00:00Z";

        StatementResult statementResult = null;
        StatementClient statementClient = null;
        String courseIdentifier = null;
        String verb = null;
        try {
            statementClient = new StatementClient(lrsURL, lrsUsername,
                    lrsPassword);
            statementResult = statementClient.filterBySince(dateQuery)
                    .limitResults(LIMIT).getStatements();

            log.info("Electronicmailaddress : " + courseIdentifier);
            log.info("Electronicmailaddress: " + getLastToken(mbox, ":"));
            ContactInformation contactInformation = contactInformationSvc
                    .getContactInformationByElectronicmailaddress(
                            getLastToken(mbox, ":"));
            log.info("PersonId : " + contactInformation.getPersonid());
            // Selecting base don Mbox value
            Actor a = new Agent();
            a.setMbox(mbox);
            StatementResult result = statementClient.filterByActor(a)
                    .limitResults(LIMIT).getStatements();
            log.info("" + statementResult.getStatements());
            for (Statement statement : result.getStatements()) {
                log.info("Actor: " + statement.getActor());
                log.info("Verb: " + statement.getVerb());
                log.info("Authority: " + statement.getAuthority());
                log.info("Object: " + statement.getObject());
                log.info("Object Type: "
                        + statement.getObject().getObjectType());
                log.info("Result: " + statement.getResult());
                log.info("Attachments: " + statement.getAttachments());
                Calendar statementTimestamp = javax.xml.bind.DatatypeConverter
                        .parseDateTime(statement.getTimestamp());
                log.info("Statement Date:" + statementTimestamp);
                if (statement.getObject() != null
                        && "Activity".equalsIgnoreCase(
                                statement.getObject().getObjectType())) {
                    Activity activity = (Activity) statement.getObject();
                    courseIdentifier = getLastToken(activity.getId(), "/");
                    log.info("courseIdentifier : " + courseIdentifier);
                    courseIdentifier = URLDecoder.decode(courseIdentifier,
                            "UTF-8");
                    log.info("after decoding courseIdentifier : "
                            + courseIdentifier);
                    verb = getLastToken(statement.getVerb().getId(), "/");
                    log.info("verb after tokenizer: " + verb);
                    Course course = courseSvc
                            .getCourseByCourseidentifier(courseIdentifier);
                    LearnerProfile learnerProfile = new LearnerProfile();
                    learnerProfile.setCourseid(course.getCourseid());
                    learnerProfile
                            .setPersonid(contactInformation.getPersonid());
                    learnerProfile.setActivitystatus(verb);
                    learnerProfileSvc.save(learnerProfile);

                }

            }
            log.info("End Of All Statements ################ ");
            response = "xAPI data loaded to ELRR Stage Database";
        } catch (Exception e) {
            response = e.getMessage();
          }

        return ResponseEntity.ok(response);
    }

    private String getLastToken(final String strValue, final String splitter) {
        String[] strArray = strValue.split(splitter);
        return strArray[strArray.length - 1];
    }
}
