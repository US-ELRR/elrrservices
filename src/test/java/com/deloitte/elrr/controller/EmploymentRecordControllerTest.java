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

import com.deloitte.elrr.dto.EmploymentRecordDto;
import com.deloitte.elrr.entity.EmploymentRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(EmploymentRecordController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class EmploymentRecordControllerTest extends CommonControllerTest {

    
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

    private static final UUID employmentRecordId = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllEmploymentRecordsTest() throws Exception {

        Mockito.doReturn(getEmploymentRecordList()).when(getEmploymentRecordSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employmentrecord")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmploymentRecordDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<EmploymentRecordDto>>() { });
        assertEquals(employmentRecordId, result.get(0).getId());
    }

    
    @Test
    void getEmploymentRecordByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getEmploymentRecordList().iterator().next()))
                .when(getEmploymentRecordSvc()).get(employmentRecordId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employmentrecord/"+employmentRecordId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        EmploymentRecordDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<EmploymentRecordDto>() { });
        assertEquals(result.getId(), employmentRecordId);
    }

    
    @Test
    void getEmploymentRecordByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employmentrecord/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    
    @Test
    void getEmploymentRecordByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getEmploymentRecordList().iterator().next()))
                .when(getEmploymentRecordSvc()).get(employmentRecordId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employmentrecord?id="+employmentRecordId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<EmploymentRecordDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<EmploymentRecordDto>>() { });
        assertEquals(employmentRecordId, result.get(0).getId());
    }

    @Test
    void updateEmploymentRecordTest() throws Exception {
        EmploymentRecordDto employmentRecordDto = new EmploymentRecordDto();
        employmentRecordDto.setId(employmentRecordId);
        Mockito.doReturn(Optional.of(getEmploymentRecordList().iterator().next()))
                .when(getEmploymentRecordSvc()).get(employmentRecordId);
        Mockito.doReturn(getEmploymentRecordList().iterator().next()).when(getEmploymentRecordSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/employmentrecord/"+employmentRecordId).accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(employmentRecordDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        EmploymentRecordDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<EmploymentRecordDto>() { });
        assertEquals(result.getId(), employmentRecordId);
    }

    @Test
    void deleteEmploymentRecordTest() throws Exception {
        Mockito.doReturn(Optional.of(getEmploymentRecordList().iterator().next()))
                .when(getEmploymentRecordSvc()).get(employmentRecordId);
        Mockito.doNothing().when(getEmploymentRecordSvc()).delete(employmentRecordId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/employmentrecord/"+employmentRecordId).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /**
     *
     * @return Iterable<EmploymentRecordDto>
     */
    private static Iterable<EmploymentRecord> getEmploymentRecordList() {
        List<EmploymentRecord> employmentRecordList = new ArrayList<>();
        EmploymentRecord employmentRecord = new EmploymentRecord();
        employmentRecord.setId(employmentRecordId);
        employmentRecordList.add(employmentRecord);

        return employmentRecordList;
    }
}
