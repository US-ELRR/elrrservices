/**
 * 
 */
package com.deloitte.elrr.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deloitte.elrr.DemoApplication;
import com.deloitte.elrr.dto.CourseDto;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.jpa.svc.CourseSvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class CourseControllerTest {

	@MockBean
	private CourseSvc courseSvc;

	@MockBean
	ModelMapper mapper;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	void getAllCoursesTest() throws Exception {
		List<Course> courses = new ArrayList<>();
		Course course = new Course();
		courses.add(course);
		Mockito.when(courseSvc.findAll()).thenReturn(courses);
		List<CourseDto> courseDtos = new ArrayList<>();
		CourseDto courseDto = mapper.map(courses.get(0), CourseDto.class);
		courseDtos.add(courseDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void getCourseByIdTest() throws Exception {
		Course course = new Course();
		Mockito.when(courseSvc.get(Mockito.anyLong())).thenReturn(Optional.of(course));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/12")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		assertNotNull(mvcResult);
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);

	}
}