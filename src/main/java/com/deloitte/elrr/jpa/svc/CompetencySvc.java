package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.repository.CompetencyRepository;

@Service
public class CompetencySvc implements CommonSvc<Competency, UUID> {
    /**
     *
     */
    private final CompetencyRepository competencyRepository;
    /**
     *
     * @param argsCompetencyRepository
     */
    public CompetencySvc(
            final CompetencyRepository argsCompetencyRepository) {
        this.competencyRepository = argsCompetencyRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Competency, UUID> getRepository() {
        return this.competencyRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Competency competency) {
        return competency.getId();
    }
    /**
     *
     */
    @Override
    public Competency save(final Competency competency) {
        return CommonSvc.super.save(competency);
    }

}
