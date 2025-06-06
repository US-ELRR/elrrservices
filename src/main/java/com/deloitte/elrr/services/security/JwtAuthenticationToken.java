package com.deloitte.elrr.services.security;

import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Implementation of AbstractAuthenticationToken Specifically for carrying
 * JWT details with security context.
 */
public class JwtAuthenticationToken  extends AbstractAuthenticationToken {

    private DecodedJWT jwt;

    /**
     * Construct Auth Token. Will set authenticated to true.
     *
     * @param auths Granted Authorities
     * @param jwt Decoded JWT
     */
    public JwtAuthenticationToken(List<SystemAuthority> auths, DecodedJWT jwt) {
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
        //TODO Figure out canonical username claims for both token types
        return jwt.getClaim("username");
    }

    /**
     * @param key
     * @return Claim value if key exists in token
     */
    public Object getClaim(String key) {
        return jwt.getClaim(key);
    }

}
