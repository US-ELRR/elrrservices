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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.entity.Location;
import com.deloitte.elrr.services.dto.LocationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(LocationController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class LocationControllerTest extends CommonControllerTest {

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

    private static final UUID LOCATION_ID = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllLocationsTest() throws Exception {

        Mockito.doReturn(getLocationList()).when(getLocationSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/location")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<LocationDto> result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<LocationDto>>() {
                });
        assertEquals(LOCATION_ID, result.get(0).getId());
    }

    @Test
    void getLocationByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getLocationList().iterator().next()))
                .when(getLocationSvc()).get(LOCATION_ID);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/location/" + LOCATION_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        LocationDto result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<LocationDto>() {
                });
        assertEquals(result.getId(), LOCATION_ID);
    }

    @Test
    void getLocationByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/location/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    void getLocationByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getLocationList().iterator().next()))
                .when(getLocationSvc()).get(LOCATION_ID);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/location?id=" + LOCATION_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        List<LocationDto> result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<LocationDto>>() {
                });
        assertEquals(LOCATION_ID, result.get(0).getId());
    }

    @Test
    void createLocationTest() throws Exception {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(LOCATION_ID);
        Mockito.doReturn(getLocationList().iterator().next())
                .when(getLocationSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/location")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(locationDto))
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(201, mvcResult.getResponse().getStatus());
        LocationDto result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<LocationDto>() {
                });
        assertEquals(result.getId(), LOCATION_ID);
    }

    @Test
    void updateLocationTest() throws Exception {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(LOCATION_ID);
        Mockito.doReturn(Optional.of(getLocationList().iterator().next()))
                .when(getLocationSvc()).get(LOCATION_ID);
        Mockito.doReturn(getLocationList().iterator().next())
                .when(getLocationSvc())
                .save(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/location/" + LOCATION_ID)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(locationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(200, mvcResult.getResponse().getStatus());
        LocationDto result = resultsAsObject(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<LocationDto>() {
                });
        assertEquals(result.getId(), LOCATION_ID);
    }

    @Test
    void deleteLocationTest() throws Exception {
        Mockito.doReturn(Optional.of(getLocationList().iterator().next()))
                .when(getLocationSvc()).get(LOCATION_ID);
        Mockito.doNothing().when(getLocationSvc()).delete(LOCATION_ID);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/location/" + LOCATION_ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    /**
     *
     * @return Iterable<LocationDto>
     */
    private static Iterable<Location> getLocationList() {
        List<Location> locationList = new ArrayList<>();
        Location location = new Location();
        location.setId(LOCATION_ID);
        locationList.add(location);

        return locationList;
    }
}
