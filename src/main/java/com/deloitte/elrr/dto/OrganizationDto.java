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
public class OrganizationDto implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = 1L;
    /**
    *
    */
    private long organizationid;
    /**
    *
    */
    @NotNull
    @Size(max = 100)
    private String organizationname;
    /**
    *
    */
    @Size(max = 100)
    private String organizationidentifier;
    /**
    *
    */
    @Size(max = 100)
    private String organizationidentificationcode;
    /**
    *
    */
    @Size(max = 100)
    private String organizationidentificationsystem;
    /**
    *
    */
    @Size(max = 100)
    private String industrytypeidentifier;
    /**
    *
    */
    @Size(max = 100)
    private String organizationfein;
    /**
    *
    */
    private String organizationdescription;
    /**
    *
    */
    @Size(max = 100)
    private String parentorganization;
    /**
    *
    */
    @Size(max = 10)
    private String recordstatus;

}
