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
    /**
     *
     */
    private final CourseRepository courseRepository;
    /**
     *
     * @param argsCourseRepository
     */
    public CourseSvc(final CourseRepository argsCourseRepository) {
        this.courseRepository = argsCourseRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Course, Long> getRepository() {
        return this.courseRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final Course course) {
        return course.getCourseid();
    }
    /**
     *
     */
    @Override
    public Course save(final Course course) {
        return CommonSvc.super.save(course);
    }
    /**
     *
     * @param courseidentifier
     * @return Course
     */
    public Course getCourseByCourseidentifier(final String courseidentifier) {
        return this.courseRepository.findIdByCourseidentifier(courseidentifier);
    }

}
