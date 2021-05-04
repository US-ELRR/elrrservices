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
public class AccreditationDto implements Serializable {
	         
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accreditationid;
	private String organizationaccredits;
	private String accreditedby;
	private String accreditationtype;
	private String accreditationadminprocess;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date accreditationawarddate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date accreditationexpirationdate;
	private String recordstatus;
}