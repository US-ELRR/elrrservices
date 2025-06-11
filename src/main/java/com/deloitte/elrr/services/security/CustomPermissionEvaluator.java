package com.deloitte.elrr.services.security;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.deloitte.elrr.services.dto.PermissionDto;

@Slf4j
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication,
            Object resource, Object action) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        // get permissions from token
        List<PermissionDto> permissions = token.getPermissions();
        // if at least one permission matches the resource and its actions
        // include the action, return true, otherwise return false
        if (permissions != null && !permissions.isEmpty()) {
            return permissions.stream()
                .anyMatch(permission ->
                    permission
                        .getResource()
                        .equals((String) resource)
                    &&
                    permission
                        .getActions()
                        .contains((PermissionDto.Action) action));
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
            Serializable targetId, String targetType, Object permission) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        //implment permission logic based on JWT claims here
        return true;
    }

    /*
    might use later...

    private boolean verifyRole(JwtAuthenticationToken token, SystemRole role) {
        GrantedAuthority apiAuth = token.getAuthorities().stream()
            .filter(auth -> auth.getAuthority().equals(role.name()))
            .findAny()
            .orElse(null);
        return apiAuth != null;
    }*/

}
