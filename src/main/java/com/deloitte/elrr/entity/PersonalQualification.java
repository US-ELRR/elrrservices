package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person_qualification")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Getter
@Setter
public class PersonalQualification extends Auditable<String> {

    @ManyToOne
    @JoinColumn(name="person_id")
    public Person person;
    
    @Column(name = "has_record")
    public Boolean hasRecord;

}
