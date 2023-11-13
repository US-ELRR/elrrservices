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

import com.deloitte.elrr.dto.LearnerProfileDto;
import com.deloitte.elrr.entity.LearnerProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(LearnerProfileController.class)
@ContextConfiguration
@WithMockUser
public class LearnerProfileControllerTest extends CommonControllerTest {

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
    void getAllLearnerProfilesTest() throws Exception {

        Mockito.doReturn(getLearnerProfileList()).when(getLearnerProfileSvc())
                .findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learnerprofilefact")
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
    void getAllLearnerProfilesErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learnerprofilefact")
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
    void getLearnerProfileByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getLearnerProfileList().iterator().next()))
                .when(getLearnerProfileSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learnerprofilefact/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getLearnerProfileByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learnerprofilefact/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getLearnerProfileByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getLearnerProfileList().iterator().next()))
                .when(getLearnerProfileSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/learnerprofilefact?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createLearnerProfileTest() throws Exception {
        LearnerProfileDto learnerProfileDto = new LearnerProfileDto();
        learnerProfileDto.setPersonid(1L);
        Mockito.doReturn(getLearnerProfileList().iterator().next())
                .when(getLearnerProfileSvc())
                .save(getLearnerProfileList().iterator().next());

        mockMvc.perform(post("/api/learnerprofilefact/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(learnerProfileDto)));
                //.andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateLearnerProfileTest() throws Exception {
        LearnerProfileDto learnerProfileDto = new LearnerProfileDto();
        learnerProfileDto.setPersonid(1L);
        Mockito.doReturn(Optional.of(getLearnerProfileList().iterator().next()))
                .when(getLearnerProfileSvc()).get(1L);
        Mockito.doReturn(getLearnerProfileList().iterator().next())
                .when(getLearnerProfileSvc())
                .save(getLearnerProfileList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/learnerprofilefact/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(learnerProfileDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/learnerprofilefact/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(learnerProfileDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateLearnerProfileErrorTest() throws Exception {
        LearnerProfileDto learnerProfileDto = new LearnerProfileDto();
        learnerProfileDto.setPersonid(1L);

        Mockito.doReturn(getLearnerProfileList().iterator().next())
                .when(getLearnerProfileSvc())
                .save(getLearnerProfileList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/learnerprofilefact/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(learnerProfileDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/learnerprofilefact/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(learnerProfileDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteLearnerProfileTest() throws Exception {

        Mockito.doNothing().when(getLearnerProfileSvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/learnerprofilefact/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteLearnerProfileErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/learnerprofilefact/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<LearnerProfileDto>
     */
    private static Iterable<LearnerProfile> getLearnerProfileList() {
        List<LearnerProfile> learnerProfileList = new ArrayList<>();
        LearnerProfile learnerProfile = new LearnerProfile();
        learnerProfile.setLearnerprofileid(1L);
        learnerProfileList.add(learnerProfile);
        Collection<LearnerProfile> collections = learnerProfileList;
        Iterable<LearnerProfile> iterable = collections;
        return iterable;
    }

}
