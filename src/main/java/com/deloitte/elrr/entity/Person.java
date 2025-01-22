package com.deloitte.elrr.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person extends Auditable<String> {
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "name_prefix")
    private String namePrefix;

    @Column(name = "title_affix_code")
    private String titleAffixCode;

    @Column(name = "name_suffix")
    private String nameSuffix;

    @Column(name = "qualification_affix_code")
    private String qualificationAffixCode;

    @Column(name = "maiden_name")
    private String maidenName;

    @ManyToOne
    @JoinColumn(name="mailing_address_id")
    private Location mailingAddress;

    //mailing_address_id -- locations
    //physical_address_id
    //shipping_address_id
    //billing_address_id
    //on_campus_address_id
    //off_campus_address_id
    //temporary_address_id
    //permanent_student_address_id
    //employment_address_id
    //time_of_admission_address_id
    //father_address_id
    //mother_address_id
    //guardian_address_id
    //birthplace

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "height")
    private BigDecimal height;
    
    @Column(name = "height_unit")
    private String heightUnit;
    
    @Column(name = "weight")
    private String weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Column(name = "interpupillary_distance")
    private Long interpupillaryDistance;

    @Column(name = "handedness")
    private String handedness;

    @Column(name = "primary_language")
    private String primaryLanguage;

    @Column(name = "current_security_clearance")
    private String currentSecurityClearance;

    @Column(name = "highest_security_clearance")
    private String highestSecurityClearance;

    @Column(name = "union_membership")
    private Boolean unionMembership;

    @Override
    public String toString() {
        return "Person [name=" + name + ", firstName=" + firstName + ", id=" + id + ", middleName=" + middleName
                + ", lastName=" + lastName + ", namePrefix=" + namePrefix + ", titleAffixCode=" + titleAffixCode
                + ", nameSuffix=" + nameSuffix + ", qualificationAffixCode=" + qualificationAffixCode + ", maidenName="
                + maidenName + ", birthdate=" + birthdate + ", citizenship=" + citizenship + ", height=" + height
                + ", heightUnit=" + heightUnit + ", weight=" + weight + ", weightUnit=" + weightUnit
                + ", interpupillaryDistance=" + interpupillaryDistance + ", handedness=" + handedness
                + ", primaryLanguage=" + primaryLanguage + ", currentSecurityClearance=" + currentSecurityClearance
                + ", highestSecurityClearance=" + highestSecurityClearance + ", unionMembership=" + unionMembership
                + "]";
    }

}
