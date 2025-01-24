package com.deloitte.elrr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "email")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email extends Auditable<String> {
    
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "email_address_type")
    private String emailAddressType;

    @Override
    public String toString() {
        return "Email [emailAddress=" + emailAddress + ", emailAddressType=" + emailAddressType + ", id=" + id + "]";
    }
}
