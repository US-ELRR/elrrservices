package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Employment;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

}
