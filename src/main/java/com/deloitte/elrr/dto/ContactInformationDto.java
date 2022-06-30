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
public class ContactInformationDto implements Serializable {

    /**
     * contactinformationid.
     */
    private long contactinformationid;
    /**
     * personid.
     */
    private long personid;
    /**
     * contactinformationData.
     */
    private String contactinformationData;
    /**
     * telephonenumber.
     */
    private String telephonenumber;
    /**
     * isprimaryindicator.
     */
    private String isprimaryindicator;
    /**
     * telephonetype.
     */
    private String telephonetype;
    /**
     * electronicmailaddress.
     */
    private String electronicmailaddress;
    /**
     * electronicmailaddresstype.
     */
    private String electronicmailaddresstype;
    /**
     * emergencycontact.
     */
    private String emergencycontact;
    /**
     * recordstatus.
     */
    private String recordstatus;
}
