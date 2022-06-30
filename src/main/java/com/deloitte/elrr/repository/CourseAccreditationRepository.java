package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.CourseAccreditation;

@Repository
public interface CourseAccreditationRepository
        extends JpaRepository<CourseAccreditation, Long> {

}
