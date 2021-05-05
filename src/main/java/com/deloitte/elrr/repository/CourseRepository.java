package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	public Course findIdByCourseidentifier(final String courseidentifier);

}