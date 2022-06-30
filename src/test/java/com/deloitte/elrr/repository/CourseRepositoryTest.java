package com.deloitte.elrr.repository;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.elrr.entity.Course;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
class CourseRepositoryTest {

    /**
     *
     */
    @Mock
    private CourseRepository mockCourseRepository;

    /**
     *
     */
    @Mock
    private Course course;


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {

        //MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {

        course.setCoursenumber("1");
        mockCourseRepository.save(course);
    }

}
