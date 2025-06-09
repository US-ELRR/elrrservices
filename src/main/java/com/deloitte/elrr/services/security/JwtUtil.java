package com.deloitte.elrr.services.security;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deloitte.elrr.services.dto.PermissionDto;

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
        String creatorUname = "";
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            creatorUname = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        }
        return JWT.create()
            .withIssuer(ISSUER)
            .withIssuedAt(new Date())
            .withClaim(CREATOR_KEY, creatorUname)
            .sign(getAlgorithm());
    }

    /**
     * Create a new Client Token with permissions.
     * @param permissions List of permissions to be added as a claim in the token
     * @return JWT Token String
     */
    public String createToken(List<PermissionDto> permissions) {
        String creatorUname = "";
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            creatorUname = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
        }
        List<Map<String, Object>> permissionsAsMap = permissions.stream()
            .map(PermissionDto::toMap)
            .collect(Collectors.toList());

        return JWT.create()
            .withIssuer(ISSUER)
            .withIssuedAt(new Date())
            .withClaim(CREATOR_KEY, creatorUname)
            .withClaim("elrr_permissions", permissionsAsMap)
            .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC512(secret);
        }
        return algorithm;
    }
}
