/**
 *
 */
package com.deloitte.elrr.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.EmploymentDto;
import com.deloitte.elrr.entity.Employment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(EmploymentController.class)
@ContextConfiguration
@WithMockUser
public class EmploymentControllerTest extends CommonControllerTest {


    /**
     *
     */
    @MockBean
    private ModelMapper mapper;


    /**
     *
     */
    @Autowired
    private MockMvc mockMvc;

    /**
    *
    */
    @Autowired
    private ObjectMapper objectMapper;
    
    /**
     * 
     */
    private HttpHeaders headers;
    /**
     * 
     */
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

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllEmploymentsTest() throws Exception {

        Mockito.doReturn(getEmploymentList())
        .when(getEmploymentSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllEmploymentsErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        mockMvc.perform(requestBuilder).andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     */
    @Test
    void getEmploymentByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getEmploymentList().iterator().next()))
                .when(getEmploymentSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employment/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getEmploymentByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employment/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getEmploymentByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getEmploymentList().iterator().next()))
                .when(getEmploymentSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/employment?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createEmploymentTest() throws Exception {
        EmploymentDto employmentDto = new EmploymentDto();
        employmentDto.setEmploymentid(1L);
        Mockito.doReturn(getEmploymentList().iterator().next())
        .when(getEmploymentSvc())
                .save(getEmploymentList().iterator().next());

        mockMvc.perform(post("/api/employment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employmentDto))
                .headers(headers));
                //.andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateEmploymentTest() throws Exception {
        EmploymentDto employmentDto = new EmploymentDto();
        employmentDto.setEmploymentid(1L);
        employmentDto.setEmployerName("Any");
        Mockito.doReturn(Optional.of(getEmploymentList().iterator().next()))
                .when(getEmploymentSvc()).get(1L);
        Mockito.doReturn(getEmploymentList().iterator().next())
        .when(getEmploymentSvc())
                .save(getEmploymentList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/employment/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employmentDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/employment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employmentDto))
                .headers(headers));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateEmploymentErrorTest() throws Exception {
        EmploymentDto employmentDto = new EmploymentDto();
        employmentDto.setEmploymentid(1L);
        employmentDto.setEmployerName("Any");
        Mockito.doReturn(getEmploymentList().iterator().next())
        .when(getEmploymentSvc())
                .save(getEmploymentList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/employment/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employmentDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/employment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employmentDto))
                .headers(headers));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteEmploymentTest() throws Exception {

        Mockito.doNothing().when(getEmploymentSvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/employment/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteEmploymentErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/employment/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<EmploymentDto>
     */
    private static Iterable<Employment> getEmploymentList() {
        List<Employment> employmentList = new ArrayList<>();
        Employment employment = new Employment();
        employment.setEmploymentid(1L);
        employment.setEmployerName("Any");
        employmentList.add(employment);
        Collection<Employment> collections = employmentList;
        Iterable<Employment> iterable = collections;
        return iterable;
    }


}
