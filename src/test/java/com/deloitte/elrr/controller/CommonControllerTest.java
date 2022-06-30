/**
 *
 */
package com.deloitte.elrr.controller;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.deloitte.elrr.jpa.svc.AccreditationSvc;
import com.deloitte.elrr.jpa.svc.CompetencySvc;
import com.deloitte.elrr.jpa.svc.ContactInformationSvc;
import com.deloitte.elrr.jpa.svc.CourseAccreditationSvc;
import com.deloitte.elrr.jpa.svc.CourseSvc;
import com.deloitte.elrr.jpa.svc.EmploymentSvc;
import com.deloitte.elrr.jpa.svc.LearnerProfileSvc;
import com.deloitte.elrr.jpa.svc.OrganizationSvc;
import com.deloitte.elrr.jpa.svc.PersonSvc;
import com.deloitte.elrr.repository.AccreditationRepository;
import com.deloitte.elrr.repository.CompetencyRepository;
import com.deloitte.elrr.repository.CourseRepository;
import com.deloitte.elrr.repository.EmploymentRepository;
import com.deloitte.elrr.repository.LearnerProfileRepository;
import com.deloitte.elrr.repository.OrganizationRepository;
import com.deloitte.elrr.repository.PersonalRepository;
import com.deloitte.elrr.svc.LearnerCreatorSvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author mnelakurti
 *
 */
@Getter
@Setter
@NoArgsConstructor
class CommonControllerTest {

   /**
    *
    */
   @MockBean
   private AccreditationSvc accreditationSvc;

   /**
    *
    */
   @MockBean
   private CompetencySvc competencySvc;

   /**
    *
    */
   @MockBean
   private ContactInformationSvc contactInformationSvc;

   /**
    *
    */
   @MockBean
   private CourseAccreditationSvc courseAccreditationSvc;

   /**
    *
    */
   @MockBean
   private CourseSvc courseSvc;

   /**
    *
    */
   @MockBean
   private EmploymentSvc employmentSvc;

   /**
    *
    */
   @MockBean
   private LearnerProfileSvc learnerProfileSvc;

   /**
    *
    */
   @MockBean
   private OrganizationSvc organizationSvc;

   /**
    *
    */
   @MockBean
   private LearnerCreatorSvc learnerCreatorSvc;
   /**
    *
    */
   @MockBean
   private PersonSvc personSvc;

   /**
   *
   */
   @MockBean
   private AccreditationRepository accreditationRepository;

   /**
   *
   */
   @MockBean
   private CourseRepository courseRepository;

   /**
   *
   */
   @MockBean
   private CompetencyRepository competencyRepository;

   /**
   *
   */
   @MockBean
   private EmploymentRepository employmentRepository;

   /**
   *
   */
   @MockBean
   private LearnerProfileRepository learnerProfileRepository;

   /**
   *
   */
   @MockBean
   private OrganizationRepository organizationRepository;

   /**
   *
   */
   @MockBean
   private PersonalRepository personalRepository;

   /**
   *
   * @param obj
   * @return String
   * @throws JsonProcessingException
   */
  public static String asJsonString(final Object obj)
          throws JsonProcessingException {

      return new ObjectMapper().writeValueAsString(obj);

  }
}

