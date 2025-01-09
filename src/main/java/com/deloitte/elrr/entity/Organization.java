package com.deloitte.elrr.entity;

import java.util.UUID;

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
@Table(name = "ORGANIZATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organization extends Auditable<String> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
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

    //organization_accredits

    /**
     *
     */
    @Override
    public String toString() {
        return "Organization [id=" + id + ", organizationName="
                + name + "]";
    }

}
