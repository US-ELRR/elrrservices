package com.deloitte.elrr.services.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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

            //TODO Identify Client JWT and validate client JWT but leave
            //proxy/p1 user claims unvalidated
            DecodedJWT jwt = jwtUtil.getToken(jwtStr);

            if (jwt != null) {
                List<SystemAuthority> authList =
                    new ArrayList<SystemAuthority>();
                //TODO set appropriate role type for key type
                authList.add(new SystemAuthority(SystemRole.ADMIN));
                JwtAuthenticationToken authToken = new JwtAuthenticationToken(
                    authList, jwt);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
