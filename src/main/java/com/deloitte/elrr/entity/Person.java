package com.deloitte.elrr.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
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

    @ManyToOne
    @JoinColumn(name="physical_address_id")
    private Location physicalAddress;

    @ManyToOne
    @JoinColumn(name="shipping_address_id")
    private Location shippingAddress;

    @ManyToOne
    @JoinColumn(name="billing_address_id")
    private Location billingAddress;

    @ManyToOne
    @JoinColumn(name="on_campus_address_id")
    private Location onCampusAddress;

    @ManyToOne
    @JoinColumn(name="off_campus_address_id")
    private Location offCampusAddress;

    @ManyToOne
    @JoinColumn(name="permanent_student_address_id")
    private Location permanentStudentAddress;

    @ManyToOne
    @JoinColumn(name="employment_address_id")
    private Location employmentAddress;

    @ManyToOne
    @JoinColumn(name="time_of_admission_address_id")
    private Location timeOfAdmissionAddress;

    @ManyToOne
    @JoinColumn(name="father_address_id")
    private Location fatherAddress;

    @ManyToOne
    @JoinColumn(name="mother_address_id")
    private Location motherAddress;

    @ManyToOne
    @JoinColumn(name="guardian_address_id")
    private Location guardianAddress;

    @ManyToOne
    @JoinColumn(name="birthplace_id")
    private Location birthplaceAddress;

    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToMany
    @JoinTable(name="person_phone",
        joinColumns=@JoinColumn(name="person_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="phone_id", referencedColumnName="id"))
    private Set<Phone> phoneNumbers;

    @ManyToMany
    @JoinTable(name="person_email",
        joinColumns=@JoinColumn(name="person_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="email_id", referencedColumnName="id"))
    private Set<Email> emailAddresses;

    @OneToMany(mappedBy="person")
    private Set<PersonalCompetency> competencies;

    @OneToMany(mappedBy="person")
    private Set<PersonalCredential> credentials;

    @OneToMany(mappedBy="person")
    private Set<LearningRecord> learningRecords;

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
