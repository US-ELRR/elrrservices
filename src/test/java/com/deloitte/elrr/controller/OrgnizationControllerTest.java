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
import com.deloitte.elrr.dto.OrganizationDto;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.jpa.svc.OrganizationSvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author mnelakurti
 *
 */
@SpringBootTest(classes = ElrrApplication.class)
@RunWith(SpringRunner.class)
public class OrgnizationControllerTest {
	
	@MockBean
	private OrganizationSvc organizationSvc;

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
	 void getAllOrgnizationsTest() throws Exception {
		List<Organization> organizations = new ArrayList<>();
		Organization organization= new Organization();
		organizations.add(organization);
		 Mockito.when(organizationSvc.findAll()).thenReturn(organizations);
		 List<OrganizationDto> organizationDto = new ArrayList<>();
		  OrganizationDto dto = mapper.map(organizations.get(0), OrganizationDto.class);
		  organizationDto.add(dto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/organization")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertNotNull(mvcResult);
	}
	@Test
	 void getOrgnizationByIdTest() throws Exception {
		Organization organization= new Organization();
		 Mockito.when(organizationSvc.get(Mockito.anyLong())).thenReturn(Optional.of(organization));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/organization/12")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		assertNotNull(mvcResult);
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	    
        return new ObjectMapper().writeValueAsString(obj);
   
   }
}