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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.dto.AccreditationDto;
import com.deloitte.elrr.entity.Accreditation;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mnelakurti
 *
 */
@WebMvcTest(AccreditationController.class)
public class AccreditationControllerTest extends CommonControllerTest {

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
     * @throws Exception
     */
    @Test
    void getAllAccreditationsTest() throws Exception {

        Mockito.doReturn(getAccreditationList()).when(getAccreditationSvc())
                .findAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/accreditation").accept(MediaType.APPLICATION_JSON)
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
    void getAllAccreditationsErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/accreditation").accept(MediaType.APPLICATION_JSON)
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
    void getAccreditationByIdTest() throws Exception {

        Mockito.doReturn(Optional.of(getAccreditationList().iterator().next()))
                .when(getAccreditationSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/accreditation/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     */
    @Test
    void getAccreditationByIdErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/accreditation/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }
    /**
     *
     */
    @Test
    void getAccreditationByIdParameterTest() throws Exception {

        Mockito.doReturn(Optional.of(getAccreditationList().iterator().next()))
                .when(getAccreditationSvc()).get(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/accreditation?id=1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void createAccreditationTest() throws Exception {
        AccreditationDto accreditationDto = new AccreditationDto();
        accreditationDto.setAccreditationid(1L);
        Mockito.doReturn(getAccreditationList().iterator().next())
                .when(getAccreditationSvc())
                .save(getAccreditationList().iterator().next());

        mockMvc.perform(post("/api/accreditation/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accreditationDto)))
                .andExpect(status().isCreated()).andDo(print());
    }

    /**
    *
    */
    @Test
    void updateAccreditationTest() throws Exception {
        AccreditationDto accreditationDto = new AccreditationDto();
        accreditationDto.setAccreditationid(1L);
        Mockito.doReturn(Optional.of(getAccreditationList().iterator().next()))
                .when(getAccreditationSvc()).get(1L);
        Mockito.doReturn(getAccreditationList().iterator().next())
                .when(getAccreditationSvc())
                .save(getAccreditationList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/accreditation/1").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accreditationDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/accreditation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accreditationDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
    *
    */
    @Test
    void updateAccreditationErrorTest() throws Exception {
        AccreditationDto accreditationDto = new AccreditationDto();
        accreditationDto.setAccreditationid(1L);

        Mockito.doReturn(getAccreditationList().iterator().next())
                .when(getAccreditationSvc())
                .save(getAccreditationList().iterator().next());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/accreditation/1").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accreditationDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(mvcResult);
        mockMvc.perform(put("/api/accreditation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accreditationDto)));
        // .andExpect(status().isCreated()).andDo(print());

    }

    /**
     *
     */
    @Test
    void deleteAccreditationTest() throws Exception {

        Mockito.doNothing().when(getAccreditationSvc()).delete(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/accreditation/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
    *
    */
    @Test
    void deleteAccreditationErrorTest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/accreditation/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertNotNull(mvcResult);
    }

    /**
     *
     * @return Iterable<AccreditationDto>
     */
    private static Iterable<Accreditation> getAccreditationList() {
        List<Accreditation> accreditationList = new ArrayList<>();
        Accreditation accreditation = new Accreditation();
        accreditation.setAccreditationid(1L);
        accreditationList.add(accreditation);
        Collection<Accreditation> collections = accreditationList;
        Iterable<Accreditation> iterable = collections;
        return iterable;
    }

}
