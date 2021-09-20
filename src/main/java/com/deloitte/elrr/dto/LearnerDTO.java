package com.deloitte.elrr.dto;

import java.util.List;

import com.deloitte.elrr.entity.Learner;

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

public class LearnerDTO {

	String role;
	Learner learner;
	List<Learner> assigned;
	
}
