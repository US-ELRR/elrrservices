package com.deloitte.elrr.entity;

import java.util.List;

import com.deloitte.elrr.dto.ContactInformationDto;
import com.deloitte.elrr.dto.EmploymentDto;
import com.deloitte.elrr.dto.OrganizationDto;
import com.deloitte.elrr.dto.PersonDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class  Personnel{

	private PersonDto person;
	private OrganizationDto organization;
	private ContactInformationDto contactInformation;
	private List<EmploymentDto> employment;

		
	@Override
	public String toString() {
		return "Personnel [personal=" + person + ", organization=" + organization + ", employment=" + employment
				+ "]";
	}
}
