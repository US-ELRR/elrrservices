package com.deloitte.elrr.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CREDENTIAL")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalCredential extends PersonalQualification {

    @ManyToOne
    @JoinColumn(name="qualification_id")
    private Credential credential;

    public PersonalCredential(Person p, Credential c, Boolean hasRecord){
        this.setCredential(c);
        this.setHasRecord(hasRecord);
        this.setPerson(p);
    }

    //@Override
    //public AbstractQualification getQualification() {
    //    return credential;
    //}

}
