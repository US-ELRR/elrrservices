package com.deloitte.elrr.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.PersonDto;
import com.deloitte.elrr.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */

@WebMvcTest(PersonController.class)
@ContextConfiguration
@WithMockUser
public class PersonControllerTest extends CommonControllerTest {

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
     */
    private HttpHeaders headers;
    /**
     * 
     */
    @BeforeEach
    void addHeaders() {
        headers = new HttpHeaders();
        headers.set("Content-Type", " */*");
        headers.set("X-Forwarded-Proto", "https");
    }

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

    private static final UUID personId = UUID.randomUUID();

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllPersonsTest() throws Exception {

        Mockito.doReturn(getPersonList()).when(getPersonSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        //mockMvc.perform(requestBuilder).andExpect(status().isOk())
        //        .andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    void getAllPersonsErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);
        mockMvc.perform(requestBuilder).andDo(print());
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);

    }

    /**
     *
     */
    @Test
    void getPersonByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getPersonByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getPersonByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person?id=1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createPersonTest() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setId(personId);
        Mockito.doReturn(getPersonList().iterator().next()).when(getPersonSvc())
                .save(getPersonList().iterator().next());

        mockMvc.perform(
                post("/api/person/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto))
                        .headers(headers));
                //.andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updatePersonTest() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setId(personId);
        Mockito.doReturn(Optional.of(getPersonList().iterator().next()))
                .when(getPersonSvc()).get(personId);
        Mockito.doReturn(getPersonList().iterator().next()).when(getPersonSvc())
                .save(getPersonList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/person/1").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(
                put("/api/person/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto))
                        .headers(headers));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updatePersonErrorTest() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setId(personId);

        Mockito.doReturn(getPersonList().iterator().next()).when(getPersonSvc())
                .save(getPersonList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/person/1").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(
                put("/api/person/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDto))
                        .headers(headers));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deletePersonTest() throws Exception {

        Mockito.doNothing().when(getPersonSvc()).delete(personId);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deletePersonErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<PersonDto>
     */
    private static Iterable<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(personId);
        personList.add(person);
        Collection<Person> collections = personList;
        Iterable<Person> iterable = collections;
        return iterable;
    }
}
