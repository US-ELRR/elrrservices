package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CONTACTINFORMATION")
//, schema="CMTR")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactInformation extends Auditable<String> {
    /**
    * contactinformationid.
    */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactinformationid;
    /**
    * personid.
    */
    @Column(name = "personid")
    private long personid;
    /**
    * contactinformationData.
    */
    @Column(name = "contactinformation")
    private String contactinformationData;
    /**
    * telephonenumber.
    */
    @Column(name = "telephonenumber")
    private String telephonenumber;
    /**
    * isprimaryindicator.
    */
    @Column(name = "isprimaryindicator")
    private String isprimaryindicator;
    /**
    * telephonetype.
    */
    @Column(name = "telephonetype")
    private String telephonetype;
    /**
    * electronicmailaddress.
    */
    @Column(name = "electronicmailaddress")
    private String electronicmailaddress;
    /**
    * electronicmailaddresstype.
    */
    @Column(name = "electronicmailaddresstype")
    private String electronicmailaddresstype;
    /**
    * emergencycontact.
    */
    @Column(name = "emergencycontact")
    private String emergencycontact;
    /**
    * recordstatus.
    */
    @Column(name = "recordstatus")
    private String recordstatus;
}
