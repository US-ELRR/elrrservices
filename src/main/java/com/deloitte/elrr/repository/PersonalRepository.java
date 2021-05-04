package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Person;

@Repository
public interface PersonalRepository  extends JpaRepository<Person, Long>{

}
