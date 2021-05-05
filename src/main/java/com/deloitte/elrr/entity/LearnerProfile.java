package com.deloitte.elrr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="LEARNERPROFILE")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class LearnerProfile extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long learnerprofilefactid;
	@Column(name = "personid")
	private long personid;
	@Column(name = "learneraddressid")
	private long learneraddressid;
	@Column(name = "contactinformationid")
	private long contactinformationid;
	@Column(name = "employmentid")
	private long employmentid;
	@Column(name = "positionid")
	private long  positionid;
	@Column(name = "citizenshipid")
	private long citizenshipid;
	@Column(name = "studentid")
	private long studentid;
	@Column(name = "courseid")
	private long courseid;
	@Column(name = "courseaccreditationid")
	private long courseaccreditationid;	
	@Column(name = "competencyid")
	private long competencyid;
	@Column(name = "credentialid")
	private long credentialid;
	@Column(name = "organizationid")
	private long organizationid;
	@Column(name = "organizationaddressid")
	private long organizationaddressid;
	@Column(name = "accreditationid")
	private long accreditationid;
	@Column(name = "activitystatus")
	private String activitystatus;
	@Column(name = "recordstatus")
	private String recordstatus;          
}
