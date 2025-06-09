package com.deloitte.elrr.services.controller;

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
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.entity.MilitaryRecord;
import com.deloitte.elrr.services.TestAppConfig;
import com.deloitte.elrr.services.dto.MilitaryRecordDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(MilitaryRecordController.class)
@Import(TestAppConfig.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class MilitaryRecordControllerTest extends CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private HttpHeaders headers;

    private static final String MILITARY_RECORD_API = "/api/militaryrecord";

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

    private static final UUID MILITARY_RECORD_ID = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllMilitaryRecordsTest() throws Exception {

        Mockito.doReturn(getMilitaryRecordList()).when(getMilitaryRecordSvc())
                .findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(MILITARY_RECORD_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<MilitaryRecordDto> result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<MilitaryRecordDto>>() {
                });
        assertEquals(MILITARY_RECORD_ID, result.get(0).getId());
    }

    @Test
    void getMilitaryRecordByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getMilitaryRecordList().iterator().next()))
                .when(getMilitaryRecordSvc()).get(MILITARY_RECORD_ID);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(MILITARY_RECORD_API + "/" + MILITARY_RECORD_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        MilitaryRecordDto result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<MilitaryRecordDto>() {
                });
        assertEquals(result.getId(), MILITARY_RECORD_ID);
    }

    @Test
    void getMilitaryRecordByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(MILITARY_RECORD_API + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void getMilitaryRecordByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getMilitaryRecordList().iterator().next()))
                .when(getMilitaryRecordSvc()).get(MILITARY_RECORD_ID);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(MILITARY_RECORD_API + "?id=" + MILITARY_RECORD_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<MilitaryRecordDto> result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<MilitaryRecordDto>>() {
                });
        assertEquals(MILITARY_RECORD_ID, result.get(0).getId());
    }

    @Test
    void updateMilitaryRecordTest() throws Exception {
        MilitaryRecordDto militaryRecordDto = new MilitaryRecordDto();
        militaryRecordDto.setId(MILITARY_RECORD_ID);
        militaryRecordDto.setBranch("TestBranch");
        militaryRecordDto.setCountry("TestCountry");
        Mockito.doReturn(Optional.of(getMilitaryRecordList().iterator().next()))
                .when(getMilitaryRecordSvc()).get(MILITARY_RECORD_ID);
        Mockito.doReturn(getMilitaryRecordList().iterator().next())
                .when(getMilitaryRecordSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(MILITARY_RECORD_API + "/" + MILITARY_RECORD_ID)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(militaryRecordDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        MilitaryRecordDto result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<MilitaryRecordDto>() {
                });
        assertEquals(result.getId(), MILITARY_RECORD_ID);
    }

    @Test
    void deleteMilitaryRecordTest() throws Exception {
        Mockito.doReturn(Optional.of(getMilitaryRecordList().iterator().next()))
                .when(getMilitaryRecordSvc()).get(MILITARY_RECORD_ID);
        Mockito.doNothing().when(getMilitaryRecordSvc())
                .delete(MILITARY_RECORD_ID);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(MILITARY_RECORD_API + "/" + MILITARY_RECORD_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /**
     *
     * @return Iterable<MilitaryRecordDto>
     */
    private static Iterable<MilitaryRecord> getMilitaryRecordList() {
        List<MilitaryRecord> militaryRecordList = new ArrayList<>();
        MilitaryRecord militaryRecord = new MilitaryRecord();
        militaryRecord.setId(MILITARY_RECORD_ID);
        militaryRecordList.add(militaryRecord);

        return militaryRecordList;
    }
}
