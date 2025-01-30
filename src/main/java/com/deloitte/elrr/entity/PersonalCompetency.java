package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMPETENCY")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalCompetency extends PersonalQualification {

    @ManyToOne
    @JoinColumn(name="qualification_id")
    private Competency competency;

    public PersonalCompetency(Person p, Competency c, Boolean hasRecord){
        this.setCompetency(c);
        this.setHasRecord(hasRecord);
        this.setPerson(p);
    }

    //@Override
    //public AbstractQualification getQualification() {
    //    return competency;
    //}

}
