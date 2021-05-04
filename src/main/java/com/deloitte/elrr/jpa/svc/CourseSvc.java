/**
 * 
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.repository.CourseRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class CourseSvc implements CommonSvc<Course, Long> {
	private final CourseRepository courseRepository;

	public CourseSvc(final CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public CrudRepository<Course, Long> getRepository() {
		return this.courseRepository;
	}

	@Override
	public Long getId(Course course) {
		return course.getCourseid();
	}

	@Override
	public Course save(Course course) {
		return CommonSvc.super.save(course);
	}

}
