package com.deloitte.elrr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.deloitte.elrr.dto.EmailDto;
import com.deloitte.elrr.entity.Email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(EmailController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class EmailControllerTest extends CommonControllerTest {

    
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

    private static final UUID emailId = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllEmailsTest() throws Exception {

        Mockito.doReturn(getEmailList()).when(getEmailSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/email")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmailDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<EmailDto>>() { });
        assertEquals(emailId, result.get(0).getId());
    }

    
    @Test
    void getEmailByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getEmailList().iterator().next()))
                .when(getEmailSvc()).get(emailId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/email/"+emailId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        EmailDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<EmailDto>() { });
        assertEquals(result.getId(), emailId);
    }

    
    @Test
    void getEmailByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/email/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    
    @Test
    void getEmailByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getEmailList().iterator().next()))
                .when(getEmailSvc()).get(emailId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/email?id="+emailId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmailDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<EmailDto>>() { });
        assertEquals(emailId, result.get(0).getId());
    }

    @Test
    void createEmailTest() throws Exception {
        EmailDto emailDto = new EmailDto();
        emailDto.setId(emailId);
        Mockito.doReturn(getEmailList().iterator().next()).when(getEmailSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/email")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(emailDto))
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(201, mvcResult.getResponse().getStatus());
        EmailDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<EmailDto>() { });
        assertEquals(result.getId(), emailId);
    }

    @Test
    void updateEmailTest() throws Exception {
        EmailDto emailDto = new EmailDto();
        emailDto.setId(emailId);
        Mockito.doReturn(Optional.of(getEmailList().iterator().next()))
                .when(getEmailSvc()).get(emailId);
        Mockito.doReturn(getEmailList().iterator().next()).when(getEmailSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/email/"+emailId).accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(emailDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        EmailDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<EmailDto>() { });
        assertEquals(result.getId(), emailId);
    }

    @Test
    void deleteEmailTest() throws Exception {
        Mockito.doReturn(Optional.of(getEmailList().iterator().next()))
                .when(getEmailSvc()).get(emailId);
        Mockito.doNothing().when(getEmailSvc()).delete(emailId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/email/"+emailId).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /**
     *
     * @return Iterable<EmailDto>
     */
    private static Iterable<Email> getEmailList() {
        List<Email> emailList = new ArrayList<>();
        Email email = new Email();
        email.setId(emailId);
        emailList.add(email);

        return emailList;
    }
}
