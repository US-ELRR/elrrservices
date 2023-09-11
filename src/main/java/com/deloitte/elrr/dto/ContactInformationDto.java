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
    @NotNull
    @Size(max = 20)
    private String contactinformationData;
    /**
     * telephonenumber.
     */
    @Size(max = 20)
    private String telephonenumber;
    /**
     * isprimaryindicator.
     */
    @Size(max = 1)
    private String isprimaryindicator;
    /**
     * telephonetype.
     */
    @Size(max = 20)
    private String telephonetype;
    /**
     * electronicmailaddress.
     */
    @Size(max = 254)
    private String electronicmailaddress;
    /**
     * electronicmailaddresstype.
     */
    @Size(max = 20)
    private String electronicmailaddresstype;
    /**
     * emergencycontact.
     */
    @Size(max = 250)
    private String emergencycontact;
    /**
     * recordstatus.
     */
    @Size(max = 10)
    private String recordstatus;
}
