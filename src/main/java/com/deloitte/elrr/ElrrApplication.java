package com.deloitte.elrr;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.deloitte.elrr.repository.OrganizationRepository;
import com.deloitte.elrr.repository.PersonRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@Import({ JacksonAutoConfiguration.class })
@EnableEncryptableProperties
public class ElrrApplication  {

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(ElrrApplication.class, args);
    }
    /**
     *
     */
    @Autowired
    private PersonRepository personalRepository;
    /**
     *
     */
    @Autowired
    private OrganizationRepository organizationRepository;

    /**
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    /**
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

}
