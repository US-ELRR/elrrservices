package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.LearnerProfile;

@Repository
public interface LearnerProfileRepository
        extends JpaRepository<LearnerProfile, Long> {

}
