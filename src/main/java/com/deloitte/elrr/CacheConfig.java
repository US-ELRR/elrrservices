package com.deloitte.elrr;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrations;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class CacheConfig {

	//@Value("${lrs.samlid}")
	private String samlid;// = "elrrsamltest";
	//@Value("${lrs.samlurl}")
	private String samlurl;// = "https://idp.ssocircle.com";


    /**
     * access IDP using lrs.samlurl and lrs.samlid application properties.
     * @return InMemoryRelyingPartyRegistrationRepository instance
     * @throws Exception
     */
    @Bean
    public RelyingPartyRegistrationRepository relyingPartyRegistrations()
            throws Exception {
        // if (samlid.isEmpty() || samlurl.isEmpty()) {
                return null;
        // }
        // RelyingPartyRegistration relyingPartyRegistration =
        // RelyingPartyRegistrations
        // .fromMetadataLocation(samlurl).registrationId(samlid).build();

        // return new InMemoryRelyingPartyRegistrationRepository(
        //         relyingPartyRegistration);
    }

    /**
     * add authentication and authorization.
     * @param http
     * @return http build
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http)
            throws Exception {

        // if (samlid.isEmpty() || samlurl.isEmpty()) {
            http.authorizeHttpRequests(
                    authorize -> authorize.anyRequest().authenticated());
            http.formLogin(withDefaults());
        // } else {
        //     http.authorizeHttpRequests(
        //         authorize -> authorize.anyRequest().authenticated())
        //             .saml2Metadata(withDefaults())
        //             .saml2Login(saml2 -> {
        //                 try {
        //                     saml2
        //                             .relyingPartyRegistrationRepository(
        //                                     relyingPartyRegistrations());
        //                 } catch (Exception e) {
        //                     e.printStackTrace();
        //                 }
        //             });
        // }

        return http.build();

    }
}
