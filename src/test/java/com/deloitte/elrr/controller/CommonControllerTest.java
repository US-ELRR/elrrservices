/**
 *
 */
package com.deloitte.elrr.controller;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.deloitte.elrr.jpa.svc.OrganizationSvc;
import com.deloitte.elrr.jpa.svc.PersonSvc;
import com.deloitte.elrr.repository.OrganizationRepository;
import com.deloitte.elrr.repository.PersonRepository;

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

 
   @MockBean
   private OrganizationSvc organizationSvc;
 
   @MockBean
   private PersonSvc personSvc;

   @MockBean
   private OrganizationRepository organizationRepository;

   /**
   *
   */
   @MockBean
   private PersonRepository personalRepository;

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

