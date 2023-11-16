/**
 *
 */
package com.deloitte.elrr.controller;

import java.net.URLDecoder;

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
        String response = "LRS statement data Loaded";
        StatementClient statementClient = null;
        String courseIdentifier = null;
        String verb = null;
        try {
            statementClient = new StatementClient(lrsURL, lrsUsername,
                    lrsPassword);
            ContactInformation contactInformation = contactInformationSvc
                    .getContactInformationByElectronicmailaddress(
                            getLastToken(mbox, ":"));
            // Selecting base don Mbox value
            Actor a = new Agent();
            a.setMbox(mbox);
            StatementResult result = statementClient.filterByActor(a)
                    .limitResults(LIMIT).getStatements();
             for (Statement statement : result.getStatements()) {

                if (statement.getObject() != null
                        && "Activity".equalsIgnoreCase(
                                statement.getObject().getObjectType())) {
                    Activity activity = (Activity) statement.getObject();
                    courseIdentifier = getLastToken(activity.getId(), "/");

                    courseIdentifier = URLDecoder.decode(courseIdentifier,
                            "UTF-8");
                    verb = getLastToken(statement.getVerb().getId(), "/");
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
            response = "xAPI data loaded to ELRR Stage Database";
        } catch (Exception e) {
            log.error("Error occurred when getting elrr stage data: ", e);
            response = e.getMessage();
          }

        return ResponseEntity.ok(response);
    }

    private String getLastToken(final String strValue, final String splitter) {
        String[] strArray = strValue.split(splitter);
        return strArray[strArray.length - 1];
    }
}
