/**
 * 
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

public class PersonDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8031955138252824918L;
	
	private long personid;
	private String name;
	private String firstName;
	private String middleName;
	private String lastName;
	private String namePrefix;
	private String titleAffixcode;
	private String nameSuffix;
	private String qualificationAffixcode;
	private String maidenName;
	private String preferredName;
	private String humanResourceIdentifier;
	private String personnelIdentificationSystem;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date  birthdate;
	private String sex;
	private String primaryLanguage;
	private String militaryVeteranindicator;
	private String recordStatus;

	@Override
	public String toString() {
		return "PersonDto [id=" + personid + ", name=" + name + ",firstName=" + firstName + ", middleName=" + middleName
				+ ",lastName=" + lastName + ",  namePrefix=" + namePrefix + ",   titleAffixcode=" + titleAffixcode
				+ ",nameSuffix=" + nameSuffix + ",qualificationAffixcode=" + qualificationAffixcode + ",maidenName="
				+ maidenName + ",preferredName=" + preferredName + ", humanResourceIdentifier="
				+ humanResourceIdentifier + ",        	personnelIdentificationSystem=" + personnelIdentificationSystem
				+ ",  	birthdate=" + birthdate + ", sex=" + sex + ", primaryLanguage=" + primaryLanguage
				+ ",       militaryVeteranindicator=" + militaryVeteranindicator + ",       	recordStatus="
				+ recordStatus + "]";
	}
}