package com.deloitte.elrr.services.controller;

import java.util.UUID;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.deloitte.elrr.entity.ClientToken;
import com.fasterxml.uuid.Generators;
import com.deloitte.elrr.jpa.svc.ClientTokenSvc;
import com.deloitte.elrr.services.dto.ClientTokenDto;
import com.deloitte.elrr.services.dto.PermissionsWrapperDto;
import com.deloitte.elrr.services.exception.ResourceNotFoundException;
import com.deloitte.elrr.services.security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
@Slf4j
public class ClientTokenController {
    /**
    *
    */
    @Autowired
    private ClientTokenSvc clientTokenSvc;
    /**
     *
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Create a new client token.
     *
     * @param wrapper Contains list of permissions to be included in the token
     * @return ResponseEntity<ClientTokenDto> containing the generated token
     * @throws ResourceNotFoundException if token creation fails
     */
    @PostMapping("/token")
    public ResponseEntity<ClientTokenDto> createToken(
            @Valid @RequestBody PermissionsWrapperDto wrapper)
            throws ResourceNotFoundException {

        UUID tokenId = Generators.timeBasedEpochRandomGenerator().generate();
        String token = jwtUtil.createToken(tokenId, wrapper.getPermissions());
        // get the payload back out
        // TODO figure out a way to avoid this
        DecodedJWT decodedJWT = jwtUtil.decodeToken(token);
        Map<String, Object> payload = decodedJWT.getClaims().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().as(Object.class)));
        ClientToken clientToken = new ClientToken();
        clientToken.setJwtPayload(payload);
        clientToken.setId(tokenId);
        clientTokenSvc.save(clientToken);
        // Output the token to the client
        ClientTokenDto clientTokenDto = new ClientTokenDto();
        clientTokenDto.setToken(token);

        return ResponseEntity.ok(clientTokenDto);
    }

    /**
     * Revoke (delete) a client token by its ID.
     *
     * @param tokenId The UUID of the token to revoke
     * @return ResponseEntity with no content if successful
     * @throws ResourceNotFoundException if token is not found
     */
    @DeleteMapping("/token/{tokenId}")
    public ResponseEntity<Void> revokeToken(@PathVariable UUID tokenId)
            throws ResourceNotFoundException {

        try {
            clientTokenSvc.delete(tokenId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException(
                    "Token not found with ID: " + tokenId);
        }
    }
}
