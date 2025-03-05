package com.deloitte.elrr.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.dto.CredentialDto;
import com.deloitte.elrr.dto.EmailDto;
import com.deloitte.elrr.dto.IdentityDto;
import com.deloitte.elrr.dto.LearningRecordDto;
import com.deloitte.elrr.dto.LearningResourceDto;
import com.deloitte.elrr.dto.PersonDto;
import com.deloitte.elrr.dto.PersonalQualificationDto;
import com.deloitte.elrr.dto.PhoneDto;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Credential;
import com.deloitte.elrr.entity.Email;
import com.deloitte.elrr.entity.Identity;
import com.deloitte.elrr.entity.LearningRecord;
import com.deloitte.elrr.entity.LearningResource;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.PersonalCompetency;
import com.deloitte.elrr.entity.PersonalCredential;
import com.deloitte.elrr.entity.Phone;
import com.deloitte.elrr.entity.types.LearningStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(PersonController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class PersonControllerTest extends CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private HttpHeaders headers;

    @BeforeEach
    void addHeaders() {
        headers = new HttpHeaders();
        headers.set("Content-Type", " */*");
        headers.set("X-Forwarded-Proto", "https");
    }

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

    private static final UUID personId = UUID.randomUUID();
    private static final UUID identityId = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllPersonsTest() throws Exception {

        Mockito.doReturn(getPersonList()).when(getPersonSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonDto>>() {
                });
        assertEquals(personId, result.get(0).getId());
    }

    @Test
    void getPersonByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/" + personId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        PersonDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<PersonDto>() {
                });
        assertEquals(result.getId(), personId);
    }

    @Test
    void getPersonByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void getPersonByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person?id=" + personId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonDto>>() {
                });
        assertEquals(personId, result.get(0).getId());
    }

    @Test
    void createPersonTest() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setId(personId);
        Mockito.doReturn(getPersonList().iterator().next()).when(getPersonSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDto))
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(201, mvcResult.getResponse().getStatus());
        PersonDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<PersonDto>() {
                });
        assertEquals(result.getId(), personId);
    }

    @Test
    void updatePersonTest() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setId(personId);
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        Mockito.doReturn(getPersonList().iterator().next()).when(getPersonSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/person/" + personId).accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        PersonDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<PersonDto>() {});
        assertEquals(result.getId(), personId);
    }

    @Test
    void deletePersonTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        Mockito.doNothing().when(getPersonSvc()).delete(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/" + personId).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /*
     * IDENTITY
     */

    @Test
    void getIdentitiesTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/identity")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<IdentityDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<IdentityDto>>(){});
        assertEquals(identityId, result.get(0).getId());
    }

    @Test
    void addIdentityToPersonTest() throws Exception {
        IdentityDto identityDto = new IdentityDto();
        identityDto.setId(identityId);
        Person mockPerson = getPersonList().iterator().next();
        mockPerson.setIdentities(new HashSet<Identity>());
        Mockito.doReturn(Optional.of(mockPerson)).when(getPersonSvc())
                .get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/identity")
                .content(asJsonString(identityDto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<IdentityDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<IdentityDto>>(){});
        assertEquals(identityId, result.get(0).getId());
    }

    @Test
    void deleteIdentityTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Identity mockIdentity = mockPerson.getIdentities().iterator().next();
        Mockito.when(getIdentitySvc().get(identityId)).thenReturn(Optional.of(mockIdentity));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/" + personId + "/identity/" + identityId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /*
     * PHONE
     */
    @Test
    void getAllPhonesTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/phone")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PhoneDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PhoneDto>>(){});
        assertEquals(phoneId, result.get(0).getId());
    }

    @Test
    void postPersonPhoneTest() throws Exception {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setId(phoneId);
        Person mockPerson = getPersonList().iterator().next();
        mockPerson.setPhoneNumbers(new HashSet<Phone>());
        
        Mockito.doReturn(Optional.of(mockPerson)).when(getPersonSvc())
                .get(personId);
        Mockito.when(getPhoneSvc().save(any())).thenAnswer(i -> i.getArgument(0));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/phone")
                .content(asJsonString(phoneDto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PhoneDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PhoneDto>>(){});
        assertEquals(phoneId, result.get(0).getId());
    }

    @Test
    void associatePhoneWithPersonTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Phone mockPhone = mockPerson.getPhoneNumbers().iterator().next();
        mockPerson.setPhoneNumbers(new HashSet<Phone>());
        
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));        
        Mockito.when(getPhoneSvc().get(phoneId)).thenReturn(Optional.of(mockPhone));
        Mockito.when(getPersonSvc().save(any())).thenAnswer(i -> i.getArgument(0));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/phone/"+phoneId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PhoneDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PhoneDto>>(){});
        assertEquals(phoneId, result.get(0).getId());
    }


    @Test
    void removePhoneFromPersonTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/" + personId + "/phone/" + phoneId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /*
     * EMAIL
     */
    @Test
    void getAllEmailsTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/email")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmailDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<EmailDto>>(){});
        assertEquals(emailId, result.get(0).getId());
    }

    @Test
    void postPersonEmailTest() throws Exception {
        EmailDto emailDto = new EmailDto();
        emailDto.setId(emailId);
        Person mockPerson = getPersonList().iterator().next();
        mockPerson.setEmailAddresses(new HashSet<Email>());
        
        Mockito.doReturn(Optional.of(mockPerson)).when(getPersonSvc())
                .get(personId);
        Mockito.when(getEmailSvc().save(any())).thenAnswer(i -> i.getArgument(0));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/email")
                .content(asJsonString(emailDto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmailDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<EmailDto>>(){});
        assertEquals(emailId, result.get(0).getId());
    }

    @Test
    void associateEmailWithPersonTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Email mockEmail = mockPerson.getEmailAddresses().iterator().next();
        mockPerson.setEmailAddresses(new HashSet<Email>());
        
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));        
        Mockito.when(getEmailSvc().get(emailId)).thenReturn(Optional.of(mockEmail));
        Mockito.when(getPersonSvc().save(any())).thenAnswer(i -> i.getArgument(0));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/email/"+emailId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmailDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<EmailDto>>(){});
        assertEquals(emailId, result.get(0).getId());
    }


    @Test
    void removeEmailFromPersonTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/" + personId + "/email/" + emailId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /*
     * COMPETENCY
     */
    @Test
    void getCompetenciesTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/competency")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonalQualificationDto<CompetencyDto>> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonalQualificationDto<CompetencyDto>>>(){});
        assertEquals(competencyId, result.get(0).getQualification().getId());
    }

    @Test
    void getCompetencyTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/competency/"+competencyId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        PersonalQualificationDto<CompetencyDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<PersonalQualificationDto<CompetencyDto>>(){});
        assertEquals(competencyId, result.getQualification().getId());
    }

    @Test
    void associateCompetencyTest() throws Exception {
        CompetencyDto competencyDto = new CompetencyDto();
        competencyDto.setId(competencyId);
        PersonalQualificationDto<CompetencyDto> pqd = new PersonalQualificationDto<CompetencyDto>(competencyDto, true);
        
        Person mockPerson = getPersonList().iterator().next();
        PersonalCompetency pc = mockPerson.getCompetencies().iterator().next();
        mockPerson.setCompetencies(new HashSet<PersonalCompetency>());
        
        Mockito.doReturn(Optional.of(mockPerson)).when(getPersonSvc())
                .get(personId);
        
        Mockito.when(getCompetencySvc().get(competencyId)).thenReturn(Optional.of(pc.getCompetency()));
        Mockito.when(getPersonalCompetencySvc().save(any())).thenAnswer(i -> i.getArgument(0));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/competency/"+competencyId)
                .content(asJsonString(pqd))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonalQualificationDto<CompetencyDto>> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonalQualificationDto<CompetencyDto>>>(){});
        assertEquals(competencyId, result.get(0).getQualification().getId());
        assertTrue(result.get(0).getHasRecord());
    }

    @Test
    void updateCompetencyAssociationTest() throws Exception {
        CompetencyDto competencyDto = new CompetencyDto();
        competencyDto.setId(competencyId);
        PersonalQualificationDto<CompetencyDto> pqd = new PersonalQualificationDto<CompetencyDto>(competencyDto, false);
        
        Person mockPerson = getPersonList().iterator().next();
        PersonalCompetency pc = mockPerson.getCompetencies().iterator().next();
        
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));
        Mockito.when(getCompetencySvc().get(competencyId)).thenReturn(Optional.of(pc.getCompetency()));
        Mockito.when(getPersonalCompetencySvc().save(any())).thenAnswer(i -> i.getArgument(0));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/person/"+personId+"/competency/"+competencyId)
                .content(asJsonString(pqd))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonalQualificationDto<CompetencyDto>> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonalQualificationDto<CompetencyDto>>>(){});
        assertEquals(competencyId, result.get(0).getQualification().getId());
        assertFalse(result.get(0).getHasRecord());
    }



    @Test
    void deleteCompetencyAssociationTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/" + personId + "/competency/" + competencyId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /*
     * CREDENTIAL
     */
    @Test
    void getCredentialsTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/credential")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonalQualificationDto<CredentialDto>> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonalQualificationDto<CredentialDto>>>(){});
        assertEquals(credentialId, result.get(0).getQualification().getId());
    }

    @Test
    void getCredentialTest() throws Exception {
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/credential/"+credentialId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        PersonalQualificationDto<CredentialDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<PersonalQualificationDto<CredentialDto>>(){});
        assertEquals(credentialId, result.getQualification().getId());
    }

    @Test
    void associateCredentialTest() throws Exception {
        CredentialDto credentialDto = new CredentialDto();
        credentialDto.setId(credentialId);
        PersonalQualificationDto<CredentialDto> pqd = new PersonalQualificationDto<CredentialDto>(credentialDto, true);
        
        Person mockPerson = getPersonList().iterator().next();
        PersonalCredential pc = mockPerson.getCredentials().iterator().next();
        mockPerson.setCredentials(new HashSet<PersonalCredential>());
        
        Mockito.doReturn(Optional.of(mockPerson)).when(getPersonSvc())
                .get(personId);
        
        Mockito.when(getCredentialSvc().get(credentialId)).thenReturn(Optional.of(pc.getCredential()));
        Mockito.when(getPersonalCredentialSvc().save(any())).thenAnswer(i -> i.getArgument(0));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/credential/"+credentialId)
                .content(asJsonString(pqd))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonalQualificationDto<CredentialDto>> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonalQualificationDto<CredentialDto>>>(){});
        assertEquals(credentialId, result.get(0).getQualification().getId());
        assertTrue(result.get(0).getHasRecord());
    }

    @Test
    void updateCredentialAssociationTest() throws Exception {
        CredentialDto credentialDto = new CredentialDto();
        credentialDto.setId(credentialId);
        PersonalQualificationDto<CredentialDto> pqd = new PersonalQualificationDto<CredentialDto>(credentialDto, false);
        
        Person mockPerson = getPersonList().iterator().next();
        PersonalCredential pc = mockPerson.getCredentials().iterator().next();
        
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));
        Mockito.when(getCredentialSvc().get(credentialId)).thenReturn(Optional.of(pc.getCredential()));
        Mockito.when(getPersonalCredentialSvc().save(any())).thenAnswer(i -> i.getArgument(0));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/person/"+personId+"/credential/"+credentialId)
                .content(asJsonString(pqd))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<PersonalQualificationDto<CredentialDto>> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<PersonalQualificationDto<CredentialDto>>>(){});
        assertEquals(credentialId, result.get(0).getQualification().getId());
        assertFalse(result.get(0).getHasRecord());
    }



    @Test
    void deleteCredentialAssociationTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/" + personId + "/credential/" + credentialId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /*
     * LEARNING RECORD
     */
    @Test
    void getLearningRecordsTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/"+personId+"/learningrecord")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<LearningRecordDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<LearningRecordDto>>(){});
        assertEquals(learningResourceId, result.get(0).getLearningResource().getId());
        assertEquals(LearningStatus.ATTEMPTED, result.get(0).getRecordStatus());
    }

    @Test
    void addLearningRecordTest() throws Exception {
        Person mockPerson = getPersonList().iterator().next();
        LearningResource mockLearningResource = mockPerson.getLearningRecords()
                .iterator().next().getLearningResource();
        
        LearningResourceDto learningResourceDto = new LearningResourceDto();
        learningResourceDto.setId(learningResourceId);
        LearningRecordDto learningRecordDto = new LearningRecordDto();
        learningRecordDto.setLearningResource(learningResourceDto);
        learningRecordDto.setRecordStatus(LearningStatus.ATTEMPTED);

        mockPerson.setLearningRecords(new HashSet<LearningRecord>());
        Mockito.when(getPersonSvc().get(personId)).thenReturn(Optional.of(mockPerson));
        Mockito.when(getLearningResourceSvc().get(learningResourceId))
                .thenReturn(Optional.of(mockLearningResource));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person/"+personId+"/learningrecord")
                .content(asJsonString(learningRecordDto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<LearningRecordDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<LearningRecordDto>>(){});
        assertEquals(learningResourceId, result.get(0).getLearningResource().getId());
    }



    /*
     * 
     * @me: now just duplicate the learningrecord one to employment record and military record
     * 
     * @GetMapping("/person/{personId}/employmentrecord")
     * 
     * @PostMapping("/person/{personId}/employmentrecord")
     * 
     * @GetMapping("/person/{personId}/militaryrecord")
     * 
     * @PostMapping("/person/{personId}/militaryrecord")
     * 
     * @GetMapping("/person/{personId}/organization")
     * 
     * @GetMapping("/person/{personId}/organization/{organizationId}")
     * 
     * @PostMapping("/person/{personId}/organization/{organizationId}")
     * 
     * @PutMapping("/person/{personId}/organization/{organizationId}")
     * 
     * @DeleteMapping("/person/{personId}/organization/{organizationId}")
     */

    private static final UUID phoneId = UUID.randomUUID();
    private static final UUID emailId = UUID.randomUUID();
    private static final UUID competencyId = UUID.randomUUID();
    private static final UUID credentialId = UUID.randomUUID();
    private static final UUID learningResourceId = UUID.randomUUID();

    /**
     *
     * @return Iterable<PersonDto>
     */
    private static Iterable<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(personId);

        Identity identity = new Identity();
        identity.setId(identityId);
        identity.setPerson(person);
        person.setIdentities(Collections.singleton(identity));
        
        Phone phone = new Phone();
        phone.setId(phoneId);
        person.setPhoneNumbers(Collections.singleton(phone));


        Email email = new Email();
        email.setId(emailId);
        person.setEmailAddresses(Collections.singleton(email));

        Competency competency = new Competency();
        competency.setId(competencyId);
        PersonalCompetency personalCompetency = new PersonalCompetency(person, competency, false);
        Set<PersonalCompetency> comps = new HashSet<PersonalCompetency>();
        comps.add(personalCompetency);
        person.setCompetencies(comps);

        Credential credential = new Credential();
        credential.setId(credentialId);
        PersonalCredential personalCredential = new PersonalCredential(person, credential, false);
        Set<PersonalCredential> creds = new HashSet<PersonalCredential>();
        creds.add(personalCredential);
        person.setCredentials(creds);

        LearningResource learningResource = new LearningResource();
        learningResource.setId(learningResourceId);
        LearningRecord learningRecord = new LearningRecord();
        learningRecord.setRecordStatus(LearningStatus.ATTEMPTED);
        learningRecord.setLearningResource(learningResource);
        person.setLearningRecords(Collections.singleton(learningRecord));

        personList.add(person);

        return personList;
    }
}
