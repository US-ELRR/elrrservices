package com.deloitte.elrr.dto;

import java.util.Date;

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

public class LearnerHeader {

 	Date fromDate;
	Date toDate;
	String orgName;
	String responseEntities;
	int startRecord;
	int endRecord;
	int totalCount;
	
	 }
