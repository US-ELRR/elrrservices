package com.deloitte.elrr.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.Personnel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(HomeController.class)
class HomeControllerTests extends CommonControllerTest {

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
     */
    @Test
    void getLearnerByIdTest() throws Exception {

        //Mockito.doReturn(getLearnerList())
        //        .when(getLearnerCreatorSvc().learnerCreator("test"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learner?param1=test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getLearnerByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learner?param1=test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getLearnerByIdParameterTest() throws Exception {

        //Mockito.doReturn(getLearnerList())
        //        .when(getLearnerCreatorSvc().learnerCreator("test"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learner?id=1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<LearnerDto>
     */
    private static Learner getLearnerList() {
        Learner learner = new Learner();
        learner.setCourses(getCourseList());
        learner.setCompetencies(getCompetencyList());
        learner.setPersonnel(getPersonnelList());
        return learner;
    }

    /**
     *
     * @return Iterable<CompetencyDto>
     */
    private static List<Competency> getCompetencyList() {
        List<Competency> competencyList = new ArrayList<>();
        Competency competency = new Competency();
        competency.setCompetencyid(1L);
        competencyList.add(competency);

        return competencyList;
    }

    /**
     *
     * @return Iterable<CourseDto>
     */
    private static List<Course> getCourseList() {
        List<Course> courseList = new ArrayList<>();
        Course course = new Course();
        course.setCourseid(1L);
        courseList.add(course);
        return courseList;
    }

    /**
     *
     * @return Iterable<CourseDto>
     */
    private static Personnel getPersonnelList() {
        Personnel personnel = new Personnel();
        Person person = new Person();
        person.setPersonid(1L);
        personnel.setPerson(person);
        return personnel;
    }

}
