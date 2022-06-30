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

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.repository.CompetencyRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CompetencySvcTest {

    /**
    *
    */
    @Mock
    private CompetencyRepository competencyRepository;

    /**
     *
     *
     */
    @Test
    void test() {
        CompetencySvc competencySvc = new CompetencySvc(competencyRepository);
        Competency competency = new Competency();
        competency.setCompetencyid(1L);
        List<Competency> accreditationList = new ArrayList<>();
        accreditationList.add(competency);
        ReflectionTestUtils.setField(competencySvc, "competencyRepository",
                competencyRepository);
        Mockito.doReturn(competency).when(competencyRepository)
                .save(competency);
        Mockito.doReturn(true).when(competencyRepository).existsById(1L);
        Mockito.doNothing().when(competencyRepository).deleteById(1L);

        competencySvc.getI(competency);
        competencySvc.findAll();
        competencySvc.get(1L);
        competencySvc.save(competency);
        competencySvc.deleteAll();
        competencySvc.delete(1L);
        competencySvc.update(competency);
        competencySvc.saveAll(accreditationList);
        competency.setCompetencyid(2L);
        try {
            competencySvc.update(competency);
        } catch (Exception e) {

        }
        try {
            competencySvc.delete(2L);
        } catch (Exception e) {

        }
    }

}
