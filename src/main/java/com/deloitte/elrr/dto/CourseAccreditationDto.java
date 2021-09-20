/**
 * 
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;

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
public class CourseAccreditationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long courseaccreditationid;
	private String accreditedby;
	private String accreditationtype;
	private String accreditationadminprocess;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date accreditationawarddate;
	private Date accreditationexpirationdate;
	private String recordstatus;

}