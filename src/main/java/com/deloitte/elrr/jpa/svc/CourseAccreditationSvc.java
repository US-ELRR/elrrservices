/**
 * 
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.CourseAccreditation;
import com.deloitte.elrr.repository.CourseAccreditationRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class CourseAccreditationSvc implements CommonSvc<CourseAccreditation, Long> {
	private final CourseAccreditationRepository courseaccreditationRepository;

	public CourseAccreditationSvc(final CourseAccreditationRepository courseaccreditationRepository) {
		this.courseaccreditationRepository = courseaccreditationRepository;
	}

	@Override
	public CrudRepository<CourseAccreditation, Long> getRepository() {
		return this.courseaccreditationRepository;
	}

	@Override
	public Long getId(CourseAccreditation courseaccreditation) {
		return courseaccreditation.getCourseaccreditationid();
		}

	@Override
	public CourseAccreditation save(CourseAccreditation courseaccreditation) {
		return CommonSvc.super.save(courseaccreditation);
	}

}
