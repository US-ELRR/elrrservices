package com.deloitte.elrr.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entity {

@Id
    @GenericGenerator(name = "semi_seq_uuid", strategy = "com.deloitte.elrr.util.SemiSeqUUIdGenerator")
    @GeneratedValue(generator = "semi_seq_uuid")  
    protected UUID id;

}
