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
    @NotNull
    @Size(max = 100)
    private String employerName;
    /**
    *
    */
    @Size(max = 100)
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
    @Size(max = 100)
    private String joblevel;
    /**
    *
    */
    @Size(max = 100)
    private String occupation;
    /**
    *
    */
    @Size(max = 1)
    private String employed;
    /**
    *
    */
    @Size(max = 50)
    private String primarycarrercategory;
    /**
    *
    */
    private String secondcarrercategory;
    /**
    *
    */
    @Size(max = 10)
    private String recordstatus;
}
