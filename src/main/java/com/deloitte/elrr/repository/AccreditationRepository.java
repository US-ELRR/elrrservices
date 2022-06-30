package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Accreditation;

@Repository
public interface AccreditationRepository
        extends JpaRepository<Accreditation, Long> {

}
