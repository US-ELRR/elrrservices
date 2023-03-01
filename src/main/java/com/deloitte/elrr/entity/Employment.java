package com.deloitte.elrr.entity;

import java.util.Date;

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
@Table(name = "EMPLOYMENT")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employment extends Auditable<String> {
    /**
    *
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employmentid;
    /**
    *
    */
    @Column(name = "employername")
    private String employerName;
    /**
    *
    */
    @Column(name = "employerdepartment")
    private String employerdepartment;
    /**
    *
    */
    @Column(name = "hiredate")
    private Date hiredate;
    /**
    *
    */
    @Column(name = "employmentstartdate")
    private String employmentstartdate;
    /**
    *
    */
    @Column(name = "employmentenddate")
    private Date employmentenddate;
    /**
    *
    */
    @Column(name = "joblevel")
    private String joblevel;
    /**
    *
    */
    @Column(name = "occupation")
    private String occupation;
    /**
    *
    */
    @Column(name = "employed")
    private String employed;
    /**
    *
    */
    @Column(name = "primarycarrercategory")
    private String primarycarrercategory;
    /**
    *
    */
    @Column(name = "secondcarrercategory")
    private String secondcarrercategory;
    /**
    *
    */
    @Column(name = "recordstatus")
    private String recordstatus;

}
