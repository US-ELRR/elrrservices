package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.PersonalCompetency;
import com.deloitte.elrr.repository.PersonalCompetencyRepository;

@Service
public class PersonalCompetencySvc implements CommonSvc<PersonalCompetency, UUID> {
    /**
     *
     */
    private final PersonalCompetencyRepository personalCompetencyRepository;
    /**
     *
     * @param argsCompetencyRepository
     */
    public PersonalCompetencySvc(
            final PersonalCompetencyRepository argsRepository) {
        this.personalCompetencyRepository = argsRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<PersonalCompetency, UUID> getRepository() {
        return this.personalCompetencyRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final PersonalCompetency pq) {
        return pq.getId();
    }
    /**
     *
     */
    @Override
    public PersonalCompetency save(final PersonalCompetency pq) {
        return CommonSvc.super.save(pq);
    }

}
