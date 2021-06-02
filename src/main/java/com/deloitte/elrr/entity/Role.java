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
@Table(name="Role")
//, schema="CMTR")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class Role extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleid")
 	private long roleid;
	@Column(name = "rolename")
 	private String roleName;
	@Column(name = "status")
	private String status;
	
	 
	@Override
	public String toString() {
		return "";
	}
	

}
