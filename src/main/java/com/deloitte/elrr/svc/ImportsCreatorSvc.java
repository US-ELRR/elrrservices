package com.deloitte.elrr.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deloitte.elrr.dto.ImportsDTO;

@Service
public interface ImportsCreatorSvc {

	public ImportsDTO  getImports(String name);
	public List<ImportsDTO> getAllImports();
}
