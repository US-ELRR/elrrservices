package com.deloitte.elrr.dto;

import lombok.Data;

@Data
public class ElrrException {

	FailureType failureType;
	String fieldName;
	String errorMessage;
}
