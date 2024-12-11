package com.deloitte.elrr.jpa.svc;

import com.deloitte.elrr.entity.Employment;
//import com.deloitte.elrr.entity.LearnerProfile;
import com.deloitte.elrr.repository.EmploymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmploymentSvcTest {

/**
 *
 */
@Mock
private EmploymentRepository employmentRepository;

    /**
     *
     *
     */
    @Test
    void test() {
        EmploymentSvc employmentSvc = new EmploymentSvc(
                employmentRepository);
        Employment employment = new Employment();
        employment.setEmploymentid(1L);
        List<Employment> employmentList = new ArrayList<>();
        employmentList.add(employment);
        ReflectionTestUtils.setField(employmentSvc,
                "employmentRepository", employmentRepository);
        Mockito.doReturn(employment)
                .when(employmentRepository).save(employment);
        Mockito.doReturn(true).when(employmentRepository)
                .existsById(1L);
        Mockito.doNothing().when(employmentRepository).deleteById(1L);

        employmentSvc.getI(employment);
        employmentSvc.findAll();
        employmentSvc.get(1L);
        employmentSvc.save(employment);
        employmentSvc.deleteAll();
        employmentSvc.delete(1L);
        try {
            employmentSvc.update(employment);
        } catch (Exception e) {
        }
        employmentSvc.saveAll(employmentList);
    }
}
