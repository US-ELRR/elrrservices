/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.repository.CourseRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CourseSvcTest {


        /**
        *
        */
        @Mock
        private CourseRepository courseRepository;

        /**
         *
         *
         */
        @Test
        void test() {
            CourseSvc courseSvc = new CourseSvc(
                    courseRepository);
            Course course = new Course();
            course.setCourseid(1L);
            List<Course> courseList = new ArrayList<>();
            courseList.add(course);
            ReflectionTestUtils.setField(courseSvc,
                    "courseRepository", courseRepository);
            Mockito.doReturn(course)
                    .when(courseRepository).save(course);
            Mockito.doReturn(true).when(courseRepository)
                    .existsById(1L);
            Mockito.doNothing().when(courseRepository).deleteById(1L);

            courseSvc.getI(course);
            courseSvc.findAll();
            courseSvc.get(1L);
            courseSvc.save(course);
            courseSvc.deleteAll();
            courseSvc.delete(1L);
            courseSvc.update(course);
            courseSvc.saveAll(courseList);
        }
    }
