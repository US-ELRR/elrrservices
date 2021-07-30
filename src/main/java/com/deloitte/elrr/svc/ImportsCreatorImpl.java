package com.deloitte.elrr.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.dto.ImportsDTO;
import com.deloitte.elrr.dto.ImportsDetailsDTO;
import com.deloitte.elrr.entity.Imports;
import com.deloitte.elrr.entity.ImportsDetails;
import com.deloitte.elrr.jpa.svc.ImportsDetailsService;
import com.deloitte.elrr.jpa.svc.ImportsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImportsCreatorImpl implements ImportsCreatorSvc {

	@Autowired
	ImportsService importsService;
	
	@Autowired
	ImportsDetailsService importsDetailsService;
	
	@Override
	public ImportsDTO getImports(String name) {
		
		List<ImportsDetailsDTO> importsDTOList = new ArrayList<>();
		Imports imports = importsService.findByName(name);
		List<ImportsDetails> detailsList = importsDetailsService.findByImportId(imports.getImportid());
			
		for (ImportsDetails details: detailsList) {
			ImportsDetailsDTO dto = getDto(details,imports);
			importsDTOList.add(dto);
			
		}
		ImportsDTO dto = new ImportsDTO();
		dto.setDetailsList(importsDTOList);
		dto.setImportsName(imports.getImportName());
		return dto;
	}
	
	@Override
	public List<ImportsDTO> getAllImports() {
		
		List<ImportsDTO> list = new ArrayList<>();
		
		Iterable<Imports> importsList = importsService.findAll();
		
		for (Imports imports: importsList) {
			log.info("inside imports "+imports.getImportName());
			ImportsDTO dto = new ImportsDTO();
			List<ImportsDetails> detailsList = importsDetailsService.findByImportId(imports.getImportid());
			List<ImportsDetailsDTO> detailsDTOList = new ArrayList<>();
			for (ImportsDetails details: detailsList) {
				ImportsDetailsDTO detailDto = getDto(details,imports);
				detailsDTOList.add(detailDto);
				
			}
			dto.setImportsName(imports.getImportName());
			dto.setDetailsList(detailsDTOList);
			log.info("adding Dto for "+imports.getImportName());
			list.add(dto);
		}
		return list;
		
	}
	private ImportsDetailsDTO getDto(ImportsDetails details, Imports imports) {
		ImportsDetailsDTO dto = new ImportsDetailsDTO();
		dto.setImportsDate(details.getImportBeginTime());
		dto.setFailedRecords(details.getFailedRecords());
		dto.setImportsName(imports.getImportName());
		dto.setStatus(details.getImportStatus());
		dto.setSuccessRecords(details.getSuccessRecords());
		dto.setTotalRecords(details.getTotalRecords());
		dto.setImportsEndPoint(imports.getImportName()+" End Point");
		return dto;
	}

}
