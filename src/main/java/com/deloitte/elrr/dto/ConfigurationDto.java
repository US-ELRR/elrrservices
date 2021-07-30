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
public class ConfigurationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8761453967768891305L;
	private long configurationid;
	private String configurationname;
	private String configurationvalue;
	private String recordstatus;
	private String frequency;
	private String starttime;
	private String primarycontact;
	private String primaryorgname;
	private String primaryemail;
	private String primaryphone;
	private String secondarycontact;
	private String secondaryorgname;
	private String secondaryemail;
	private String secondaryphone;
	
	 
    
}