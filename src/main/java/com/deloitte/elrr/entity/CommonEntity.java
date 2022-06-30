/**
 *
 */
package com.deloitte.elrr.entity;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author mnelakurti
 *
 */

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommonEntity extends Auditable<String> {

    /**
    * accreditedby.
    */
    @Column(name = "accreditedby")
    private String accreditedby;

    /**
    * accreditationtype.
    */
    @Column(name = "accreditationtype")
    private String accreditationtype;

    /**
    * accreditationadminprocess.
    */
    @Column(name = "accreditationadminprocess")
    private String accreditationadminprocess;

    /**
    * accreditationawarddate.
    */
    @Column(name = "accreditationawarddate")
    private Date accreditationawarddate;

    /**
    * accreditationexpirationdate.
    */
    @Column(name = "accreditationexpirationdate")
    private Date accreditationexpirationdate;
    /**
    * recordstatus.
    */
    @Column(name = "recordstatus")
    private String recordstatus;

}

