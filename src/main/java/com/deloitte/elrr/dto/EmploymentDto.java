/**
 * 
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.deloitte.elrr.entity.Employment;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.entity.Person;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author mnelakurti
 *
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

public class EmploymentDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private long employmentid;
	
	private String employerName;

	private String employerdepartment;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date  hiredate;

	private String employmentstartdate;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date employmentenddate;

	private String joblevel;

	private String occupation;

	private String employed;

	private String primarycarrercategory;

	private String secondcarrercategory;

	private String recordstatus;
}