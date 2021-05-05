package com.deloitte.elrr.entity;

import java.util.Date;

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
@Table(name="COURSEACCREDITATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class CourseAccreditation extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long courseaccreditationid;
	@Column(name = "accreditedby")
	private String accreditedby;
	@Column(name = "accreditationtype")
	private String accreditationtype;
	@Column(name = "accreditationadminprocess")
	private String  accreditationadminprocess;
	@Column(name = "accreditationawarddate")
	private Date accreditationawarddate;
	@Column(name = "accreditationexpirationdate")
	private Date accreditationexpirationdate;
	@Column(name = "recordstatus")
	private String recordstatus;
}
