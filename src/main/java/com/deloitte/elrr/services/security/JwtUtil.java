package com.deloitte.elrr.services.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {

    @Value("${client.jwt.secret}")
    private String secret;

    private Algorithm algorithm;

    private static final String ISSUER = "ELRR Client Token Authentication";
    private static final String CREATOR_KEY = "token-creator";

    /**
     * No-arg constructor for JwtUtil.
     */
    public JwtUtil() {
    }

    /**
     * Manual Constructor for unit tests compatibility.
     * @param secret injected secret param
     */
    public JwtUtil(String secret) {
        this.secret = secret;
    }

    /**
     *
     * @param jwt
     * @return If it is a jwt token or not
     */
    public DecodedJWT decodeToken(String jwt) {
        DecodedJWT djwt = JWT.decode(jwt);
        return djwt;
    }

    /**
     * @param jwt
     * @return If it is valid or not based on local secret
     */
    public DecodedJWT verify(String jwt)
            throws AlgorithmMismatchException, SignatureVerificationException {
        JWTVerifier verifier = JWT.require(getAlgorithm())
            .withIssuer(ISSUER)
            .build();
        return verifier.verify(jwt);
    }

    /**
     * Create a new Client Token.
     * @return JWT Token String
     */
    public String createToken() {
        String username = SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()
            .toString();
        return JWT.create()
            .withIssuer(ISSUER)
            .withIssuedAt(new Date())
            .withClaim(CREATOR_KEY, username)
            .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC512(secret);
        }
        return algorithm;
    }
}
