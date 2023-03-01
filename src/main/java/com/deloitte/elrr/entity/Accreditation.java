package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
