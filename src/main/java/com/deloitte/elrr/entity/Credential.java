package com.deloitte.elrr.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CREDENTIAL")
@Getter
@Setter
public class Credential extends AbstractQualification {}
