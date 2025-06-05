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

        // Add your custom column-level security logic here
        // For example, you can map roles to columns and check if the user
        // has access to the requested column
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
            Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException(
            "hasPermission with targetId is not supported");
    }

}
