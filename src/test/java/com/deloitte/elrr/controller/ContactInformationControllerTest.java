/**
 *
 *
 **/
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.ContactInformationDto;
import com.deloitte.elrr.entity.ContactInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(ContactInformationController.class)
public class ContactInformationControllerTest extends CommonControllerTest {

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
    void getAllContactInformationsTest() throws Exception {

        Mockito.doReturn(getContactInformationList())
                .when(getContactInformationSvc()).findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/contactinformation")
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
    void getAllContactInformationsErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/contactinformation")
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
    void getContactInformationByIdTest() throws Exception {

        Mockito.doReturn(
                Optional.of(getContactInformationList().iterator().next()))
                .when(getContactInformationSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/contactinformation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getContactInformationByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/contactinformation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getContactInformationByIdParameterTest() throws Exception {

        Mockito.doReturn(
                Optional.of(getContactInformationList().iterator().next()))
                .when(getContactInformationSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/contactinformation?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createContactInformationTest() throws Exception {
        ContactInformationDto contactInformationDto =
                new ContactInformationDto();
        contactInformationDto.setContactinformationid(1L);
        Mockito.doReturn(getContactInformationList().iterator().next())
                .when(getContactInformationSvc())
                .save(getContactInformationList().iterator().next());

        mockMvc.perform(post("/api/contactinformation/")
                .contentType(MediaType.APPLICATION_JSON).content(
                        objectMapper.writeValueAsString(contactInformationDto)))
                .andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateContactInformationTest() throws Exception {
        ContactInformationDto contactInformationDto =
                new ContactInformationDto();
        contactInformationDto.setContactinformationid(1L);
        Mockito.doReturn(
                Optional.of(getContactInformationList().iterator().next()))
                .when(getContactInformationSvc()).get(1L);
        Mockito.doReturn(getContactInformationList().iterator().next())
                .when(getContactInformationSvc())
                .save(getContactInformationList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/contactinformation/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactInformationDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/contactinformation/1")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper
                        .writeValueAsString(contactInformationDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateContactInformationErrorTest() throws Exception {
        ContactInformationDto contactInformationDto =
                new ContactInformationDto();
        contactInformationDto.setContactinformationid(1L);

        Mockito.doReturn(getContactInformationList().iterator().next())
                .when(getContactInformationSvc())
                .save(getContactInformationList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/contactinformation/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contactInformationDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/contactinformation/1")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper
                        .writeValueAsString(contactInformationDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteContactInformationTest() throws Exception {

        Mockito.doNothing().when(getContactInformationSvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/contactinformation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteContactInformationErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/contactinformation/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<ContactInformationDto>
     */
    private static Iterable<ContactInformation> getContactInformationList() {
        List<ContactInformation> contactInformationList = new ArrayList<>();
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setContactinformationid(1L);
        contactInformationList.add(contactInformation);
        Collection<ContactInformation> collections = contactInformationList;
        Iterable<ContactInformation> iterable = collections;
        return iterable;
    }

}
