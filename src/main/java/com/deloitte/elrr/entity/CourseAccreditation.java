package com.deloitte.elrr.entity;

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
@Table(name = "COURSEACCREDITATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseAccreditation extends CommonEntity {

    /**
    * courseaccreditationid.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseaccreditationid;

}
