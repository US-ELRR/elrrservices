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

  /**
   * access IDP using lrs.samlurl and lrs.samlid application properties.
   *
   * @return InMemoryRelyingPartyRegistrationRepository instance
   * @throws Exception
   */
  @Bean
  public RelyingPartyRegistrationRepository relyingPartyRegistrations() {
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
        throws IllegalArgumentException {
    try {
        return http.authorizeHttpRequests((authorize)
            -> authorize.anyRequest().anonymous())
            .csrf(AbstractHttpConfigurer::disable)
            .cors((cors) -> cors.configurationSource(
                apiConfigurationSource()))
            .build();
    } catch (Exception e) {
        throw new IllegalArgumentException(e);
    }
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
