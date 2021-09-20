package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.LearnerProfile;

@Repository
public interface LearnerProfileRepository extends JpaRepository<LearnerProfile, Long> {

	// @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
	// List<Long> findUserByStatusAndName(Integer status, String name);

}
