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
@Table(name = "ACCREDITATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Accreditation extends CommonEntity {

    /**
    * accreditationid.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accreditationid;

    /**
    * organizationaccredits .
    */
    @Column(name = "organizationaccredits")
    private String organizationaccredits;

}
