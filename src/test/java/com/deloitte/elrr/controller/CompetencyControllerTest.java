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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.entity.Competency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(CompetencyController.class)
@ContextConfiguration
@WithMockUser
public class CompetencyControllerTest extends CommonControllerTest {


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
    void getAllCompetencysTest() throws Exception {

        Mockito.doReturn(getCompetencyList()).when(getCompetencySvc())
                .findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/competency").accept(MediaType.APPLICATION_JSON)
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
    void getAllCompetencysErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/competency").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     */
    @Test
    void getCompetencyByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getCompetencyList().iterator().next()))
                .when(getCompetencySvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/competency/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getCompetencyByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/competency/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }
    /**
     *
     */
    @Test
    void getCompetencyByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getCompetencyList().iterator().next()))
                .when(getCompetencySvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/competency?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createCompetencyTest() throws Exception {
        CompetencyDto competencyDto = new CompetencyDto();
        competencyDto.setCompetencyid(1L);
        Mockito.doReturn(getCompetencyList().iterator().next())
                .when(getCompetencySvc())
                .save(getCompetencyList().iterator().next());

        mockMvc.perform(post("/api/competency/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDto)));
                //.andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateCompetencyTest() throws Exception {
        CompetencyDto competencyDto = new CompetencyDto();
        competencyDto.setCompetencyid(1L);
        Mockito.doReturn(Optional.of(getCompetencyList().iterator().next()))
                .when(getCompetencySvc()).get(1L);
        Mockito.doReturn(getCompetencyList().iterator().next())
                .when(getCompetencySvc())
                .save(getCompetencyList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/competency/1").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/competency/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateCompetencyErrorTest() throws Exception {
        CompetencyDto competencyDto = new CompetencyDto();
        competencyDto.setCompetencyid(1L);

        Mockito.doReturn(getCompetencyList().iterator().next())
                .when(getCompetencySvc())
                .save(getCompetencyList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/competency/1").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/competency/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(competencyDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteCompetencyTest() throws Exception {

        Mockito.doNothing().when(getCompetencySvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/competency/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteCompetencyErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/competency/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<CompetencyDto>
     */
    private static Iterable<Competency> getCompetencyList() {
        List<Competency> competencyList = new ArrayList<>();
        Competency competency = new Competency();
        competency.setCompetencyid(1L);
        competencyList.add(competency);
        Collection<Competency> collections = competencyList;
        Iterable<Competency> iterable = collections;
        return iterable;
    }


}
