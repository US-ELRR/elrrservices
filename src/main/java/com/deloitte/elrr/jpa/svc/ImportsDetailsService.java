package com.deloitte.elrr.jpa.svc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Imports;
import com.deloitte.elrr.entity.ImportsDetails;
import com.deloitte.elrr.repository.ImportsDetailsRepository;
 
@Service
public class ImportsDetailsService implements CommonSvc<ImportsDetails, Long>{

	private final ImportsDetailsRepository importsDetailsRepository;

	public ImportsDetailsService(final ImportsDetailsRepository importsDetailsRepository) {
		this.importsDetailsRepository = importsDetailsRepository;
	}

	
	public List<ImportsDetails> findByImportId(long id) {
		return importsDetailsRepository.findByImportId(id);
	}
	@Override
	public CrudRepository<ImportsDetails, Long> getRepository() {
		return this.importsDetailsRepository;
	}
 
	@Override
	public Long getId(ImportsDetails entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
