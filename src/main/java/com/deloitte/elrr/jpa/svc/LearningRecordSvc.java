package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.LearningRecord;
import com.deloitte.elrr.repository.LearningRecordRepository;

@Service
public class LearningRecordSvc implements CommonSvc<LearningRecord, UUID> {
    /**
     *
     */
    private final LearningRecordRepository learningRecordRepository;
    /**
     *
     * @param argsLearningRecordRepository
     */
    public LearningRecordSvc(
            final LearningRecordRepository argsLearningRecordRepository) {
        this.learningRecordRepository = argsLearningRecordRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<LearningRecord, UUID> getRepository() {
        return this.learningRecordRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final LearningRecord learningRecord) {
        return learningRecord.getId();
    }
    /**
     *
     */
    @Override
    public LearningRecord save(final LearningRecord learningRecord) {
        return CommonSvc.super.save(learningRecord);
    }

}
