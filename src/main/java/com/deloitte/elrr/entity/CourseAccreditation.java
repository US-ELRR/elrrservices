package com.deloitte.elrr.entity;

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
