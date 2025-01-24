package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phone")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Phone extends Auditable<String> {
    
    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "telephone_number_type")
    private String telephoneNumberType;

    @Override
    public String toString() {
        return "Phone [telephoneNumber=" + telephoneNumber + ", telephoneNumberType=" + telephoneNumberType + ", id="
                + id + "]";
    }

}
