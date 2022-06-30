/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.LearnerProfile;
import com.deloitte.elrr.repository.LearnerProfileRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class LearnerProfileSvc implements CommonSvc<LearnerProfile, Long> {
    /**
     *
     */
    private final LearnerProfileRepository learnerProfileRepository;
    /**
     *
     * @param argsLearnerProfileRepository
     */
    public LearnerProfileSvc(
            final LearnerProfileRepository argsLearnerProfileRepository) {
        this.learnerProfileRepository = argsLearnerProfileRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<LearnerProfile, Long> getRepository() {
        return this.learnerProfileRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final LearnerProfile learnerProfileFact) {
        return learnerProfileFact.getPersonid();
    }
    /**
     *
     */
    @Override
    public LearnerProfile save(final LearnerProfile learnerProfileFact) {
        return CommonSvc.super.save(learnerProfileFact);
    }

}
