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
public class CourseAccreditationSvc
        implements CommonSvc<CourseAccreditation, Long> {
    /**
     *
     */
    private final CourseAccreditationRepository courseaccreditationRepository;
    /**
     *
     * @param argsCourseaccreditationRepository
     */
    public CourseAccreditationSvc(
        final CourseAccreditationRepository argsCourseaccreditationRepository) {
        this.courseaccreditationRepository = argsCourseaccreditationRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<CourseAccreditation, Long> getRepository() {
        return this.courseaccreditationRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final CourseAccreditation courseaccreditation) {
        return courseaccreditation.getCourseaccreditationid();
    }
    /**
     *
     */
    @Override
    public CourseAccreditation save(
            final CourseAccreditation courseaccreditation) {
        return CommonSvc.super.save(courseaccreditation);
    }

}
