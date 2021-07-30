package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class ImportsDetailsDTO implements Serializable {

	String importsName;
	String importsEndPoint;
	Timestamp importsDate;
	int totalRecords;
	int failedRecords;
	int successRecords;
	String status;
}
