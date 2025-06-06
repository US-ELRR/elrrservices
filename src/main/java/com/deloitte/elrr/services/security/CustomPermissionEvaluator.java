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
        //TODO this might be a better method to use for perm specificity
        throw new UnsupportedOperationException(
            "hasPermission with targetId is not supported");
    }

}
