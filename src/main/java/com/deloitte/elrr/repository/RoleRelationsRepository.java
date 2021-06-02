package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.RoleRelations;

@Repository
public interface RoleRelationsRepository  extends JpaRepository<RoleRelations, Long>{

}
