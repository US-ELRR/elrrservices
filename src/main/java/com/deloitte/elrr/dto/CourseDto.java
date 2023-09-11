/**
 *
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 100)
    private String coursesubjectmatter;
    /**
    *
    */
    @Size(max = 20)
    private String coursesubjectabbreviation;
    /**
    *
    */
    @NotNull
    @Size(max = 50)
    private String courseidentifier;
    /**
    *
    */
    @Size(max = 50)
    private String courselevel;
    /**
    *
    */
    @Size(max = 50)
    private String coursenumber;
    /**
    *
    */
    @Size(max = 50)
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
    @Size(max = 50)
    private String courseacademicgrade;
    /**
    *
    */
    @Size(max = 100)
    private String courseprovidername;
    /**
    *
    */
    @Size(max = 100)
    private String departmentname;
    /**
    *
    */
    @Size(max = 50)
    private String coursegradescalecode;
    /**
    *
    */
    @Size(max = 50)
    private String coursemetadatarepository;
    /**
    *
    */
    @Size(max = 50)
    private String courselrsendpoint;
    /**
    *
    */
    private String coursedescription;
    /**
    *
    */
    @Size(max = 10)
    private String recordstatus;
}
