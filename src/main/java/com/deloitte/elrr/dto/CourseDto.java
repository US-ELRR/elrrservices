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

public class CourseDto implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -8761453967768891305L;
    /**
    *
    */
    private long courseid;
    /**
    *
    */
    private String name;
    /**
    *
    */
    private String coursesubjectmatter;
    /**
    *
    */
    private String coursesubjectabbreviation;
    /**
    *
    */
    private String courseidentifier;
    /**
    *
    */
    private String courselevel;
    /**
    *
    */
    private String coursenumber;
    /**
    *
    */
    private String courseinstructionmethod;
    /**
    *
    */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date coursestartdate;
    /**
    *
    */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date courseenddate;
    /**
    *
    */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date courseenrollmentdate;
    /**
    *
    */
    private String courseacademicgrade;
    /**
    *
    */
    private String courseprovidername;
    /**
    *
    */
    private String departmentname;
    /**
    *
    */
    private String coursegradescalecode;
    /**
    *
    */
    private String coursemetadatarepository;
    /**
    *
    */
    private String courselrsendpoint;
    /**
    *
    */
    private String coursedescription;
    /**
    *
    */
    private String recordstatus;
}
