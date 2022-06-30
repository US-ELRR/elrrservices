/**
 *
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;

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

public class EmploymentDto implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    /**
    *
    */
    private long employmentid;
    /**
    *
    */
    private String employerName;
    /**
    *
    */
    private String employerdepartment;
    /**
    *
    */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date hiredate;
    /**
    *
    */
    private String employmentstartdate;
    /**
    *
    */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date employmentenddate;
    /**
    *
    */
    private String joblevel;
    /**
    *
    */
    private String occupation;
    /**
    *
    */
    private String employed;
    /**
    *
    */
    private String primarycarrercategory;
    /**
    *
    */
    private String secondcarrercategory;
    /**
    *
    */
    private String recordstatus;
}
