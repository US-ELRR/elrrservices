package com.deloitte.elrr.services.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication,
            Object targetDomainObject, Object permission) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        //implment permission logic based on JWT claims here
        return true;
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
