package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long>{
	
}