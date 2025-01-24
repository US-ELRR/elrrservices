package com.deloitte.elrr.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("COMPETENCY")
@Getter
@Setter
public class Competency extends AbstractQualification {}
