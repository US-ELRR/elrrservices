package com.deloitte.elrr.services.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Implementation of AbstractAuthenticationToken Specifically for carrying admin
 * JWT details with security context.
 */
public class AdminJwtAuthenticationToken extends AbstractAuthenticationToken {

    @Value("${admin.jwt.user-id-key}")
    private String userIdKey;

    private DecodedJWT jwt;

    /**
     * Construct Auth Token. Will set authenticated to true.
     *
     * @param auths Granted Authorities
     * @param jwt Decoded JWT
     */
    public AdminJwtAuthenticationToken(List<SystemAuthority> auths,
            DecodedJWT jwt) {
        super(auths);
        super.setAuthenticated(true);
        this.jwt = jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt.getToken();
    }

    @Override
    public Object getPrincipal() {
        return jwt.getClaim(userIdKey);
    }

    /**
     * @param key
     * @return Claim value if key exists in token
     */
    public Object getClaim(String key) {
        return jwt.getClaim(key);
    }
}
