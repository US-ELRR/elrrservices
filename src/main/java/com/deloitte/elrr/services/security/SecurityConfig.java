package com.deloitte.elrr.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * @param http httpsecurity config
     * @return http security config
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/ping").permitAll()
                .anyRequest().authenticated())
            .logout((logout) -> logout.permitAll());

        http.addFilterBefore(jwtRequestFilter,
            UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*
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
  }*/

}
