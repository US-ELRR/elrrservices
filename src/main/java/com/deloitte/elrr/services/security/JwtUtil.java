package com.deloitte.elrr.services.security;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {

    /**
     *
     * @param jwt
     * @return If it is a jwt token or not
     */
    public DecodedJWT getToken(String jwt) {
        DecodedJWT djwt = JWT.decode(jwt);
        return djwt;
    }

    /**
     * @param jwt
     * @return If it is valid or not based on local secret
     */
    public boolean isLocallyValid(String jwt) {
        //TODO local secret and validation methods
        return false;
    }

}
