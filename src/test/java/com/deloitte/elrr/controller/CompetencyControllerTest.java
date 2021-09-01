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

import com.deloitte.elrr.ElrrApplication;
import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.jpa.svc.CompetencySvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author mnelakurti
 *
 */
@SpringBootTest(classes = ElrrApplication.class)
@RunWith(SpringRunner.class)
public class CompetencyControllerTest {
	
	@MockBean
	private CompetencySvc competencySvc;

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
	 void getAllCompetencysTest() throws Exception {
		List<Competency> competencys = new ArrayList<>();
		Competency competency= new Competency();
		competencys.add(competency);
		 Mockito.when(competencySvc.findAll()).thenReturn(competencys);
		 List<CompetencyDto> competencyDto = new ArrayList<>();
		  CompetencyDto dto = mapper.map(competencys.get(0), CompetencyDto.class);
		  competencyDto.add(dto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/competency")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertNotNull(mvcResult);
	}
	@Test
	 void getCompetencyByIdTest() throws Exception {
		Competency competency= new Competency();
		 Mockito.when(competencySvc.get(Mockito.anyLong())).thenReturn(Optional.of(competency));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/competency/12")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertNotNull(mvcResult);
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	    
        return new ObjectMapper().writeValueAsString(obj);
   
   }
}