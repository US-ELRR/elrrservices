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

import com.deloitte.elrr.entity.CourseAccreditation;
import com.deloitte.elrr.repository.CourseAccreditationRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CourseAccreditationSvcTest {

    /**
    *
    */
    @Mock
    private CourseAccreditationRepository courseaccreditationRepository;

    /**
     *
     *
     */
    @Test
    void test() {
        CourseAccreditationSvc courseAccreditationSvc =
                new CourseAccreditationSvc(
                courseaccreditationRepository);
        CourseAccreditation courseAccreditation = new CourseAccreditation();
        courseAccreditation.setCourseaccreditationid(1L);
        List<CourseAccreditation> courseAccreditationList = new ArrayList<>();
        courseAccreditationList.add(courseAccreditation);
        ReflectionTestUtils.setField(courseAccreditationSvc,
                "courseaccreditationRepository", courseaccreditationRepository);
        Mockito.doReturn(courseAccreditation)
                .when(courseaccreditationRepository).save(courseAccreditation);
        Mockito.doReturn(true).when(courseaccreditationRepository)
                .existsById(1L);
        Mockito.doNothing().when(courseaccreditationRepository).deleteById(1L);

        courseAccreditationSvc.getI(courseAccreditation);
        courseAccreditationSvc.findAll();
        courseAccreditationSvc.get(1L);
        courseAccreditationSvc.save(courseAccreditation);
        courseAccreditationSvc.deleteAll();
        courseAccreditationSvc.delete(1L);
        courseAccreditationSvc.update(courseAccreditation);
        courseAccreditationSvc.saveAll(courseAccreditationList);
    }
}
