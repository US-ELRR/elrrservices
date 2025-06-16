package com.deloitte.elrr.services.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deloitte.elrr.services.security.SystemAuthority.SystemRole;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String jwtStr = (authHeader != null && authHeader.startsWith("Bearer "))
            ? authHeader.substring(7) : null;

        if (SecurityContextHolder.getContext().getAuthentication() == null
                && jwtStr != null) {

            DecodedJWT jwt;
            List<SystemAuthority> authList = new ArrayList<SystemAuthority>();
            try {
                //Proper client JWT
                jwt = jwtUtil.verify(jwtStr);
                // TODO internal issuer verification
                authList.add(new SystemAuthority(SystemRole.ROLE_API));
            } catch (AlgorithmMismatchException
                    | SignatureVerificationException e) {
                //Potentially admin user JWT
                jwt = jwtUtil.decodeToken(jwtStr);

                // Check if the issuer is on the no-validation whitelist
                if (!jwtUtil.getAdminIssuerWhitelist()
                        .contains(jwt.getIssuer())) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Invalid Issuer");
                    return;
                }

                // Check role and add if it is there.
                List<String> roles = jwt.getClaim(
                    jwtUtil.getAdminRoleKey()).asList(String.class);
                if (roles.contains(jwtUtil.getAdminRole())) {
                    authList.add(new SystemAuthority(SystemRole.ROLE_ADMIN));
                }
            }

            if (jwt != null) {
                JwtAuthenticationToken authToken = new JwtAuthenticationToken(
                    authList, jwt);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
