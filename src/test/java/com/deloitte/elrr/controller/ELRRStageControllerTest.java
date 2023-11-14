/**
 *
 */
package com.deloitte.elrr.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gov.adlnet.xapi.client.StatementClient;
import gov.adlnet.xapi.model.Statement;
import gov.adlnet.xapi.model.StatementResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


/**
 * @author mnelakurti
 *
 */
@WebMvcTest(ELRRStageController.class)
@ContextConfiguration
@WithMockUser
class ELRRStageControllerTest extends CommonControllerTest {

    /**
    *
    */
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
   
    @Mock
    private StatementClient statementClient;

    /**
     *
     */
    @Mock
    private StatementResult statementResult;
    
    /**
     * 
     */
    private HttpHeaders headers;


    /**
     * 
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        headers = new HttpHeaders();
        headers.set("Content-Type", " */*");
        headers.set("X-Forwarded-Proto", "https");
    }
    @Test
    void testlocalData() throws Exception {
        when(statementClient.filterBySince("2021-01-02T00:00:00Z"))
                .thenReturn(statementClient);
        when(statementClient.getStatements()).thenReturn(statementResult);
        when(statementResult.getStatements()).thenReturn(getStatmentsList());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/elrrstagedata")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse servletResponse = mvcResult.getResponse();
        assertEquals(null, servletResponse.getErrorMessage());
    }
    @Test
    void testlocalDataSize() throws Exception {
        when(statementClient.filterBySince("2022-12-10T00:00:00Z"))
                .thenReturn(statementClient);
        when(statementResult.getStatements()).thenReturn(getStatmentsList());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/elrrstagedata/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andDo(print());
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse servletResponse = mvcResult.getResponse();
        assertEquals(null, servletResponse.getErrorMessage());
    }

    @Test
    void testlocalDataStatusOK() throws Exception {
        when(statementClient.filterBySince("2022-12-10T00:00:00Z"))
                .thenReturn(statementClient);
        when(statementResult.getStatements()).thenReturn(getStatmentsList());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/elrrstagedata/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andDo(print());
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse servletResponse = mvcResult.getResponse();
        assertEquals(null, servletResponse.getErrorMessage());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }
    @Test
    void testlocalDataStatusResult() throws Exception {
        when(statementClient.getStatements()).thenReturn(statementResult);
        when(statementResult.getStatements()).thenReturn(getStatmentsList());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/elrrstagedata/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError())
                .andDo(print());
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse servletResponse = mvcResult.getResponse();
        assertEquals(null, servletResponse.getErrorMessage());
    }

    /**
     *
     * @return List<ElrrStatement>
     */
    private static  ArrayList<Statement> getStatmentsList() {
        ArrayList<Statement> listStatments = new ArrayList<Statement>();
        Statement statement = new Statement();
        listStatments.add(statement);
        return listStatments;
    }

}
