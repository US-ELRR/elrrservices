package com.deloitte.elrr.entity;

import java.util.List;

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
	
	private List<Course> courses;
	private List<Competency> competencies;
	@Override
	public String toString() {
		return "Learner [personnel=" + personnel + ", courseList=" + courses + ", competencyList=" + competencies
				+ "]";
	}
}
