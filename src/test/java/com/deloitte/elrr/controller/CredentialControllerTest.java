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

import com.deloitte.elrr.dto.CredentialDto;
import com.deloitte.elrr.entity.Credential;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(CredentialController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class CredentialControllerTest extends CommonControllerTest {

    
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

    private static final UUID credentialId = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllCredentialsTest() throws Exception {

        Mockito.doReturn(getCredentialList()).when(getCredentialSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/credential")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<CredentialDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<CredentialDto>>() { });
        assertEquals(credentialId, result.get(0).getId());
    }

    
    @Test
    void getCredentialByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getCredentialList().iterator().next()))
                .when(getCredentialSvc()).get(credentialId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/credential/"+credentialId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        CredentialDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<CredentialDto>() { });
        assertEquals(result.getId(), credentialId);
    }

    
    @Test
    void getCredentialByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/credential/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    
    @Test
    void getCredentialByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getCredentialList().iterator().next()))
                .when(getCredentialSvc()).get(credentialId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/credential?id="+credentialId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<CredentialDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<CredentialDto>>() { });
        assertEquals(credentialId, result.get(0).getId());
    }

    @Test
    void createCredentialTest() throws Exception {
        CredentialDto credentialDto = new CredentialDto();
        credentialDto.setId(credentialId);
        Mockito.doReturn(getCredentialList().iterator().next()).when(getCredentialSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/credential")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(credentialDto))
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(201, mvcResult.getResponse().getStatus());
        CredentialDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<CredentialDto>() { });
        assertEquals(result.getId(), credentialId);
    }

    @Test
    void updateCredentialTest() throws Exception {
        CredentialDto credentialDto = new CredentialDto();
        credentialDto.setId(credentialId);
        Mockito.doReturn(Optional.of(getCredentialList().iterator().next()))
                .when(getCredentialSvc()).get(credentialId);
        Mockito.doReturn(getCredentialList().iterator().next()).when(getCredentialSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/credential/"+credentialId).accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(credentialDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        CredentialDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<CredentialDto>() { });
        assertEquals(result.getId(), credentialId);
    }

    @Test
    void deleteCredentialTest() throws Exception {
        Mockito.doReturn(Optional.of(getCredentialList().iterator().next()))
                .when(getCredentialSvc()).get(credentialId);
        Mockito.doNothing().when(getCredentialSvc()).delete(credentialId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/credential/"+credentialId).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /**
     *
     * @return Iterable<CredentialDto>
     */
    private static Iterable<Credential> getCredentialList() {
        List<Credential> credentialList = new ArrayList<>();
        Credential credential = new Credential();
        credential.setId(credentialId);
        credentialList.add(credential);

        return credentialList;
    }
}
