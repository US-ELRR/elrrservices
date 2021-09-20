package com.deloitte.elrr.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ImportsDTO  {

	String importsName;
	/*
	 * String importsEndPoint; Timestamp importsDate; int totalRecords; int
	 * failedRecords; int successRecords; String status;
	 */
	List<ImportsDetailsDTO> detailsList;
}
