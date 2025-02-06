package com.deloitte.elrr.entity;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "organization")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organization extends Auditable<String> {
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name = "profit_type")
    private String profitType;

    @Column(name = "department")
    private String department;

    @Column(name = "industry_code")
    private String industryCode;

    @Column(name = "industry_category")
    private String industryCategory;

    @Column(name = "vertical_specialization")
    private String verticalSpecialization;
    
    @Column(name = "organization_identifier")
    private String organizationIdentifier;

    //
    @Column(name = "organization_duns")
    private String organizationDUNS;

    @Column(name = "organization_fein")
    private String organizationFEIN;

    @Column(name = "school_opeid")
    private String schoolOPEID;

    @Column(name = "ipeds_type")
    private String ipedsType;

    @Column(name = "organization_isic")
    private String organizationISIC;

    @Column(name = "organization_image")
    private String organizationImage;

    @Column(name = "organization_website")
    private String organizationWebsite;

    @Column(name = "institution_level")
    private String institutionLevel;
    
    @Column(name = "institution_revocation_list")
    private String institutionRevocationList;

    @Column(name = "has_verification_service")
    private Boolean hasVerificationService;

    @Column(name = "institution_verification")
    private String institutionVerification;

    @Column(name = "organizational_resource")
    private String organizationalResource;

    @Column(name = "quality_assurance_type")
    private String qualityAssuranceType;

    @OneToMany(mappedBy="organization")
    private Set<Association> associations;

    @Override
    public String toString() {
        return "Organization [name=" + name + ", description=" + description + ", profitType=" + profitType + ", id="
                + id + ", department=" + department + ", industryCode=" + industryCode + ", industryCategory="
                + industryCategory + ", verticalSpecialization=" + verticalSpecialization + ", organizationIdentifier="
                + organizationIdentifier + ", organizationDUNS=" + organizationDUNS + ", organizationFEIN="
                + organizationFEIN + ", schoolOPEID=" + schoolOPEID + ", ipedsType=" + ipedsType + ", organizationISIC="
                + organizationISIC + ", organizationImage=" + organizationImage + ", organizationWebsite="
                + organizationWebsite + ", institutionLevel=" + institutionLevel + ", institutionRevocationList="
                + institutionRevocationList + ", hasVerificationService=" + hasVerificationService
                + ", institutionVerification=" + institutionVerification + ", organizationalResource="
                + organizationalResource + ", qualityAssuranceType=" + qualityAssuranceType + "]";
    }

    //organization_accredits

   

}
