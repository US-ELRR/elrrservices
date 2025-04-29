package com.deloitte.elrr.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class CacheConfig {

  private String samlid; // = "elrrsamltest";
  private String samlurl; // = "https://idp.ssocircle.com";

  /**
   * access IDP using lrs.samlurl and lrs.samlid application properties.
   *
   * @return InMemoryRelyingPartyRegistrationRepository instance
   * @throws Exception
   */
  @Bean
  public RelyingPartyRegistrationRepository relyingPartyRegistrations()
        throws Exception {
    return null;
  }

  /**
   * add authentication and authorization.
   *
   * @param http
   * @return http build
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http)
        throws Exception {

    // if (samlid.isEmpty() || samlurl.isEmpty()) {
    http.authorizeHttpRequests((authorize)
            -> authorize.anyRequest().anonymous())
        .csrf(AbstractHttpConfigurer::disable)
        .cors((cors) -> cors.configurationSource(apiConfigurationSource()));

    return http.build();
  }

  private CorsConfigurationSource apiConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
        "DELETE"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    UrlBasedCorsConfigurationSource source =
        new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
