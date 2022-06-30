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

import com.deloitte.elrr.entity.Accreditation;
import com.deloitte.elrr.repository.AccreditationRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccreditationSvcTest {

    /**
    *
    */
    @Mock
    private AccreditationRepository accreditationRepository;

    /**
     *
     *
     */
    @Test
    void test() {
        AccreditationSvc accreditationSvc = new AccreditationSvc(
                accreditationRepository);
        Accreditation accreditation = new Accreditation();
        accreditation.setAccreditationid(1L);
        List<Accreditation> accreditationList = new ArrayList<>();
        accreditationList.add(accreditation);
        ReflectionTestUtils.setField(accreditationSvc,
                "accreditationRepository", accreditationRepository);
        Mockito.doReturn(accreditation).when(accreditationRepository)
                .save(accreditation);
        Mockito.doReturn(true).when(accreditationRepository).existsById(1L);
        Mockito.doNothing().when(accreditationRepository).deleteById(1L);

        accreditationSvc.getI(accreditation);
        accreditationSvc.findAll();
        accreditationSvc.get(1L);
        accreditationSvc.save(accreditation);
        accreditationSvc.deleteAll();
        accreditationSvc.delete(1L);
        accreditationSvc.update(accreditation);
        accreditationSvc.saveAll(accreditationList);
    }

}
