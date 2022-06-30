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

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.CourseAccreditationDto;
import com.deloitte.elrr.entity.CourseAccreditation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(CourseAccreditationController.class)
public class CourseAccreditationControllerTest extends CommonControllerTest {

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
    void getAllCourseAccreditationsTest() throws Exception {

        Mockito.doReturn(getCourseAccreditationList())
                .when(getCourseAccreditationSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/courseaccreditation")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
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
    void getAllCourseAccreditationsErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/courseaccreditation")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     */
    @Test
    void getCourseAccreditationByIdTest() throws Exception {

        Mockito.doReturn(
                Optional.of(getCourseAccreditationList().iterator().next()))
                .when(getCourseAccreditationSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/courseaccreditation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getCourseAccreditationByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/courseaccreditation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getCourseAccreditationByIdParameterTest() throws Exception {

        Mockito.doReturn(
                Optional.of(getCourseAccreditationList().iterator().next()))
                .when(getCourseAccreditationSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/courseaccreditation?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createCourseAccreditationTest() throws Exception {
        CourseAccreditationDto courseAccreditationDto =
                new CourseAccreditationDto();
        courseAccreditationDto.setCourseaccreditationid(1L);
        Mockito.doReturn(getCourseAccreditationList().iterator().next())
                .when(getCourseAccreditationSvc())
                .save(getCourseAccreditationList().iterator().next());

        mockMvc.perform(post("/api/courseaccreditation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(courseAccreditationDto)))
                .andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateCourseAccreditationTest() throws Exception {
        CourseAccreditationDto courseAccreditationDto =
                new CourseAccreditationDto();
        courseAccreditationDto.setCourseaccreditationid(1L);
        Mockito.doReturn(
                Optional.of(getCourseAccreditationList().iterator().next()))
                .when(getCourseAccreditationSvc()).get(1L);
        Mockito.doReturn(getCourseAccreditationList().iterator().next())
                .when(getCourseAccreditationSvc())
                .save(getCourseAccreditationList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/courseaccreditation/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(courseAccreditationDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/courseaccreditation/1")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper
                        .writeValueAsString(courseAccreditationDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateCourseAccreditationErrorTest() throws Exception {
        CourseAccreditationDto courseAccreditationDto =
                new CourseAccreditationDto();
        courseAccreditationDto.setCourseaccreditationid(1L);

        Mockito.doReturn(getCourseAccreditationList().iterator().next())
                .when(getCourseAccreditationSvc())
                .save(getCourseAccreditationList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/courseaccreditation/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(courseAccreditationDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/courseaccreditation/1")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper
                        .writeValueAsString(courseAccreditationDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteCourseAccreditationTest() throws Exception {

        Mockito.doNothing().when(getCourseAccreditationSvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/courseaccreditation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteCourseAccreditationErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/courseaccreditation/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<CourseAccreditationDto>
     */
    private static Iterable<CourseAccreditation> getCourseAccreditationList() {
        List<CourseAccreditation> courseAccreditationList = new ArrayList<>();
        CourseAccreditation courseAccreditation = new CourseAccreditation();
        courseAccreditation.setCourseaccreditationid(1L);
        courseAccreditationList.add(courseAccreditation);
        Collection<CourseAccreditation> collections = courseAccreditationList;
        Iterable<CourseAccreditation> iterable = collections;
        return iterable;
    }

}
