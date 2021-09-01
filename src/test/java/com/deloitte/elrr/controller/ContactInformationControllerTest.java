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
import com.deloitte.elrr.dto.ContactInformationDto;
import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.jpa.svc.ContactInformationSvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author mnelakurti
 *
 */
@SpringBootTest(classes = ElrrApplication.class)
@RunWith(SpringRunner.class)
public class ContactInformationControllerTest {
	
	@MockBean
	private ContactInformationSvc contactInformationSvc;

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
		List<ContactInformation> contactInformations = new ArrayList<>();
		ContactInformation contactInformation= new ContactInformation();
		contactInformations.add(contactInformation);
		 Mockito.when(contactInformationSvc.findAll()).thenReturn(contactInformations);
		 List<ContactInformationDto> contactInformationDto = new ArrayList<>();
		  ContactInformationDto dto = mapper.map(contactInformations.get(0), ContactInformationDto.class);
		  contactInformationDto.add(dto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contactInformation")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertNotNull(mvcResult);
	}
	@Test
	 void getCompetencyByIdTest() throws Exception {
		ContactInformation contactInformation= new ContactInformation();
		 Mockito.when(contactInformationSvc.get(Mockito.anyLong())).thenReturn(Optional.of(contactInformation));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/contactInformation/12")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertNotNull(mvcResult);
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	    
        return new ObjectMapper().writeValueAsString(obj);
   
   }
}