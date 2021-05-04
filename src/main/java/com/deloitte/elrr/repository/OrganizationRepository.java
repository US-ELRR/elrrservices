package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Organization;
@Repository
public interface OrganizationRepository  extends JpaRepository<Organization, Long>{

}
