package com.deloitte.elrr.entity;

import java.util.List;

import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.dto.CourseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class Learner {

	private Personnel personnel; //Person, employment and organization
	
	private List<CourseDto> courses;
	private List<CompetencyDto> competencies;
	@Override
	public String toString() {
		return "Learner [personnel=" + personnel + ", courseList=" + courses + ", competencyList=" + competencies
				+ "]";
	}
}
