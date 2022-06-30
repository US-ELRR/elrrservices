/**
 *
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;

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
    private long activitystatus;
    /**
    *
    */
    private String recordstatus;
}
