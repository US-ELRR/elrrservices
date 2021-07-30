/**
 * 
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Configuration;
import com.deloitte.elrr.repository.ConfigurationRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class ConfigurationSvc implements CommonSvc<Configuration, Long> {
	private final ConfigurationRepository configurationRepository;

	public ConfigurationSvc(final ConfigurationRepository configurationRepository) {
		this.configurationRepository =configurationRepository;
	}

	@Override
	public CrudRepository<Configuration, Long> getRepository() {
		return this.configurationRepository;
	}

	@Override
	public Long getId(Configuration configuration) {
		return configuration.getConfigurationid();
	}

	@Override
	public Configuration save(Configuration configuration) {
		return CommonSvc.super.save(configuration);
	}

}
