package com.deloitte.elrr;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class HeaderFilter implements Filter {

  @Value("${http.header}")
  private boolean httpHeader;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;

    if (httpHeader = false) {
      chain.doFilter(request, response);
    } else {
      if ("https".equalsIgnoreCase(httpServletRequest.getHeader("X-Forwarded-Proto"))) {
        chain.doFilter(request, response);
      } else {
        log.error("Not a HTTPS request.");
        ((HttpServletResponse) response)
            .sendError(HttpServletResponse.SC_BAD_REQUEST, "Not a HTTPS request.");
      }
    }
  }
}
