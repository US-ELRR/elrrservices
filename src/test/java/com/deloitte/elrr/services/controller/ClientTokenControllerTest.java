package com.deloitte.elrr.services.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.elrr.services.TestAppConfig;
import com.deloitte.elrr.services.dto.PermissionDto;
import com.deloitte.elrr.services.model.Action;
import com.deloitte.elrr.services.security.JwtUtil;
import com.deloitte.elrr.services.security.MethodSecurityConfig;
import com.deloitte.elrr.services.security.SecurityConfig;


import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ClientTokenController.class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = true)
@Import({TestAppConfig.class, SecurityConfig.class, MethodSecurityConfig.class})
@Slf4j
public class ClientTokenControllerTest extends CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    private HttpHeaders headers;

    private static final String TOKEN_API = "/admin/token";

    @BeforeEach
    void addHeaders() {
        headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-Forwarded-Proto", "https");
        // Create an admin token with ROLE_ADMIN
        headers.set("Authorization",
        "Bearer " + jwtUtil.createAdminToken("external-secret"));
    }

    @Test
    void testCreateToken() throws Exception {
        // Arrange
        PermissionDto permission1 = new PermissionDto("resource1", null,
                Arrays.asList(Action.CREATE, Action.READ));
        PermissionDto permission2 = new PermissionDto("resource2", null,
                Arrays.asList(Action.UPDATE, Action.DELETE));
        List<PermissionDto> permissions =
                Arrays.asList(permission1, permission2);

        // Act
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(TOKEN_API)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"permissions\": %s}",
                asJsonString(permissions)));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testRevokeTokenSuccess() throws Exception {
        // Arrange
        UUID tokenId = UUID.randomUUID();
        
        // Act
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(TOKEN_API + "/" + tokenId)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(204, mvcResult.getResponse().getStatus());
    }

    @Test
    void testRevokeTokenNotFound() throws Exception {
        // Arrange
        UUID tokenId = UUID.randomUUID();
        
        // Mock the service to throw RuntimeServiceException when delete is called
        org.mockito.Mockito.doThrow(new com.deloitte.elrr.exception.RuntimeServiceException("Token not found"))
                .when(getClientTokenSvc()).delete(tokenId);

        // Act
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(TOKEN_API + "/" + tokenId)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

}
