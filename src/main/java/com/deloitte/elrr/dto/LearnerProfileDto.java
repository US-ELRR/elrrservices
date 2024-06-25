/**
 *
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;

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
public class LearnerProfileDto implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    /**
    *
    */
    private long personid;
    /**
    *
    */
    private long learneraddressid;
    /**
    *
    */
    private long contactinformationid;
    /**
    *
    */
    private long employmentid;
    /**
    *
    */
    private long positionid;
    /**
    *
    */
    private long citizenshipid;
    /**
    *
    */
    private long studentid;
    /**
    *
    */
    private long courseid;
    /**
    *
    */
    private long courseaccreditationid;
    /**
    *
    */
    private long competencyid;
    /**
    *
    */
    private long credentialid;
    /**
    *
    */
    private long organizationid;
    /**
    *
    */
    private long organizationaddressid;
    /**
    *
    */
    private long accreditationid;
    /**
    *
    */
    @NotNull
    @Size(max = 100)
    private String activitystatus;
    /**
    *
    */
    @Size(max = 10)
    private String recordstatus;
}
