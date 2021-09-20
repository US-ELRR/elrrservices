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
public class CompetencyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long competencyid;
	private String competencyframeworktitle;
	private String competencyframeworkversion;
	private String competencyframeworkidentifier;
	private String competencyframeworkdescription;
	private String competencyframeworksubject;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date competencyframeworkvalidstartdate;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date competencyframeworkvalidenddate;
	private String competencydefinitionidentifier;
	private String competencydefinitionidentifierurl;
	private String competencytaxonomyid;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date competencydefinitionvalidstartdate;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date competencydefinitionvalideenddate;
	private String competencydefinitionparentidentifier;
	private String competencydefinitionparenturl;
	private String competencydescriptionparentcode;
	private String competencydefinitioncode;
	private String competencydefinitionstatement;
	private String competencydefinitiontypeurl;
	private String competencydefinitiontype;
	private String recordstatus;

}