package com.deloitte.elrr.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.elrr.services.dto.ClientTokenDto;
import com.deloitte.elrr.services.dto.PermissionDto;
import com.deloitte.elrr.services.exception.ResourceNotFoundException;
import com.deloitte.elrr.services.security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
public class ClientTokenController {
    /**
     *
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     *
     * @param permissions List of permissions to be included in the token
     * @return ResponseEntity<List<PhoneDto>>
     * @throws ResourceNotFoundException
     */
    @PostMapping("/token")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientTokenDto> createToken(
            @Valid @RequestBody List<PermissionDto> permissions)
            throws ResourceNotFoundException {

        ClientTokenDto clientToken = new ClientTokenDto();
        clientToken.setToken(jwtUtil.createToken(permissions));

        return ResponseEntity.ok(clientToken);
    }
}
