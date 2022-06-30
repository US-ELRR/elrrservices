package com.deloitte.elrr.svc;

import com.deloitte.elrr.entity.Learner;

public interface LearnerCreatorSvc {
    /**
     *
     * @param personId
     * @return Learner
     */
    Learner learnerCreator(String personId);
}
