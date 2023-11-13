/**
 *
 */
package com.deloitte.elrr.controller;


import static org.hamcrest.CoreMatchers.any;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.CourseDto;
import com.deloitte.elrr.entity.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(CourseController.class)
public class CourseControllerTest extends CommonControllerTest {

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
    void getAllCoursesTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", " */*");
        headers.set("X-Forwarded-Proto", "https");

        Mockito.doReturn(getCourseList()).when(getCourseSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/course")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder.headers(headers)).andExpect(status().isOk())
                .andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllCoursesErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/course")
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
    void getCourseByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getCourseList().iterator().next()))
                .when(getCourseSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/course/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getCourseByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/course/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getCourseByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getCourseList().iterator().next()))
                .when(getCourseSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/course?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createCourseTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", " */*");
        headers.set("X-Forwarded-Proto", "https");
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseid(1L);
        //Mockito.when(mapper.map(any(), CoreMatchers.any())).doReturn(new Course());
        Mockito.doReturn(getCourseList().iterator().next()).when(getCourseSvc())
                .save(getCourseList().iterator().next());

        mockMvc.perform(post("/api/course")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDto)));
                //.andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateCourseTest() throws Exception {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseidentifier("Any");
        courseDto.setCourseid(1L);
        Mockito.doReturn(Optional.of(getCourseList().iterator().next()))
                .when(getCourseSvc()).get(1L);
        Mockito.doReturn(getCourseList().iterator().next())
                .when(getCourseSvc())
                .save(getCourseList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/course/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/course/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateCourseErrorTest() throws Exception {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseid(1L);

        Mockito.doReturn(getCourseList().iterator().next()).when(getCourseSvc())
                .save(getCourseList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/course/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/course/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteCourseTest() throws Exception {

        Mockito.doNothing().when(getCourseSvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/course/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteCourseErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/course/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<CourseDto>
     */
    private static Iterable<Course> getCourseList() {
        List<Course> courseList = new ArrayList<>();
        Course course = new Course();
        course.setCourseid(1L);
        courseList.add(course);
        Collection<Course> collections = courseList;
        Iterable<Course> iterable = collections;
        return iterable;
    }
}
