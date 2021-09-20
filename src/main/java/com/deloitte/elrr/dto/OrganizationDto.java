/**
 * 
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;

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
public class OrganizationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long organizationid;
	private String organizationname;
	private String organizationidentifier;
	private String organizationidentificationcode;
	private String organizationidentificationsystem;
	private String industrytypeidentifier;
	private String organizationfein;
	private String organizationdescription;
	private String parentorganization;
	private String recordstatus;

}