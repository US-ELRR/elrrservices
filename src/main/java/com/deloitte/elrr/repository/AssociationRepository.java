package com.deloitte.elrr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Association;

@Repository
public interface AssociationRepository
        extends JpaRepository<Association, UUID> {

}
