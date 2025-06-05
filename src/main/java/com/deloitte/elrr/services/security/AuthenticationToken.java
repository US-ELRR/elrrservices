package com.deloitte.elrr.services.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken  extends AbstractAuthenticationToken {

    private String principal;

    private String jwt;

    /**
     *
     * @param auths
     */
    public AuthenticationToken(
            Collection<? extends GrantedAuthority> auths) {
        super(auths);
    }

    /**
     *
     * @param auths
     */
    public AuthenticationToken(List<SystemAuthority> auths) {
        super(auths);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
