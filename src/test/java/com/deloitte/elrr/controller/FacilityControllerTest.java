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

import com.deloitte.elrr.dto.FacilityDto;
import com.deloitte.elrr.entity.Facility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(FacilityController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class FacilityControllerTest extends CommonControllerTest {

    
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

    private static final UUID facilityId = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllFacilitysTest() throws Exception {

        Mockito.doReturn(getFacilityList()).when(getFacilitySvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/facility")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<FacilityDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FacilityDto>>() { });
        assertEquals(facilityId, result.get(0).getId());
    }

    
    @Test
    void getFacilityByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getFacilityList().iterator().next()))
                .when(getFacilitySvc()).get(facilityId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/facility/"+facilityId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        FacilityDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<FacilityDto>() { });
        assertEquals(result.getId(), facilityId);
    }

    
    @Test
    void getFacilityByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/facility/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    
    @Test
    void getFacilityByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getFacilityList().iterator().next()))
                .when(getFacilitySvc()).get(facilityId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/facility?id="+facilityId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<FacilityDto> result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FacilityDto>>() { });
        assertEquals(facilityId, result.get(0).getId());
    }

    @Test
    void createFacilityTest() throws Exception {
        FacilityDto facilityDto = new FacilityDto();
        facilityDto.setId(facilityId);
        Mockito.doReturn(getFacilityList().iterator().next()).when(getFacilitySvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/facility")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(facilityDto))
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(201, mvcResult.getResponse().getStatus());
        FacilityDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<FacilityDto>() { });
        assertEquals(result.getId(), facilityId);
    }

    @Test
    void updateFacilityTest() throws Exception {
        FacilityDto facilityDto = new FacilityDto();
        facilityDto.setId(facilityId);
        Mockito.doReturn(Optional.of(getFacilityList().iterator().next()))
                .when(getFacilitySvc()).get(facilityId);
        Mockito.doReturn(getFacilityList().iterator().next()).when(getFacilitySvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/facility/"+facilityId).accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(facilityDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        FacilityDto result = resultsAsObject(mvcResult.getResponse().getContentAsString(), new TypeReference<FacilityDto>() { });
        assertEquals(result.getId(), facilityId);
    }

    @Test
    void deleteFacilityTest() throws Exception {
        Mockito.doReturn(Optional.of(getFacilityList().iterator().next()))
                .when(getFacilitySvc()).get(facilityId);
        Mockito.doNothing().when(getFacilitySvc()).delete(facilityId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/facility/"+facilityId).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /**
     *
     * @return Iterable<FacilityDto>
     */
    private static Iterable<Facility> getFacilityList() {
        List<Facility> facilityList = new ArrayList<>();
        Facility facility = new Facility();
        facility.setId(facilityId);
        facilityList.add(facility);

        return facilityList;
    }
}
