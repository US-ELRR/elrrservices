/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.repository.CompetencyRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class CompetencySvc implements CommonSvc<Competency, Long> {
    /**
     *
     */
    private final CompetencyRepository competencyRepository;
    /**
     *
     * @param argsCompetencyRepository
     */
    public CompetencySvc(final CompetencyRepository argsCompetencyRepository) {
        this.competencyRepository = argsCompetencyRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Competency, Long> getRepository() {
        return this.competencyRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final Competency competency) {
        return competency.getCompetencyid();
    }
    /**
     *
     */
    @Override
    public Competency save(final Competency competency) {
        return CommonSvc.super.save(competency);
    }

}
