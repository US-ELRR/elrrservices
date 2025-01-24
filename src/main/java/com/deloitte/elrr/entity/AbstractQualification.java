package com.deloitte.elrr.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "qualification")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractQualification extends Auditable<String> {

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "identifier_url")
    private String identifierUrl;

    @Column(name = "code")
    private String code;

    @Column(name = "taxonomy_id")
    private String taxonomyId;

    @Column(name = "valid_start_date")
    private Date validStartDate;

    @Column(name = "valid_end_date")
    private Date validEndDate;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "parent_url")
    private String parentUrl;

    @Column(name = "parent_code")
    private String parentCode;

    @Column(name = "type_url")
    private String typeUrl;

    @Column(name = "statement")
    private String statement;

    @Column(name = "framework_title")
    private String frameworkTitle;

    @Column(name = "framework_version")
    private String frameworkVersion;

    @Column(name = "framework_identifier")
    private String frameworkIdentifier;

    @Column(name = "framework_description")
    private String frameworkDescription;

    @Column(name = "framework_subject")
    private String frameworkSubject;

    @Column(name = "framework_valid_start_date")
    private Date frameworkValidStartDate;

    @Column(name = "framework_valid_end_date")
    private Date frameworkValidEndDate;

    @Column(name = "record_status")
    private String recordStatus;

    @Override
    public String toString() {
        return "AbstractQualification [id=" + id + ", identifier=" + identifier + ", identifierUrl=" + identifierUrl
                + ", code=" + code + ", taxonomyId=" + taxonomyId + ", validStartDate=" + validStartDate
                + ", validEndDate=" + validEndDate + ", parentId=" + parentId + ", parentUrl=" + parentUrl
                + ", parentCode=" + parentCode + ", typeUrl=" + typeUrl + ", statement=" + statement
                + ", frameworkTitle=" + frameworkTitle + ", frameworkVersion=" + frameworkVersion
                + ", frameworkIdentifier=" + frameworkIdentifier + ", frameworkDescription=" + frameworkDescription
                + ", frameworkSubject=" + frameworkSubject + ", frameworkValidStartDate=" + frameworkValidStartDate
                + ", frameworkValidEndDate=" + frameworkValidEndDate + ", recordStatus=" + recordStatus + "]";
    }

}
