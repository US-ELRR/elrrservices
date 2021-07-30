package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Imports;
import com.deloitte.elrr.repository.ImportsRepository;
 
@Service
public class ImportsService implements CommonSvc<Imports, Long>{

	private final ImportsRepository importsRepository;

	public ImportsService(final ImportsRepository importsRepository) {
		this.importsRepository = importsRepository;
	}

	public Imports findByName(String name) {
		return importsRepository.findByName(name);
	}
	@Override
	public CrudRepository<Imports, Long> getRepository() {
		return this.importsRepository;
	}

  	@Override
	public Long getId(Imports entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
