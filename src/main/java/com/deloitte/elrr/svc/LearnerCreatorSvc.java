package com.deloitte.elrr.svc;

import com.deloitte.elrr.dto.LearnerDTO;
import com.deloitte.elrr.entity.Learner;

public interface LearnerCreatorSvc {

	public LearnerDTO learnerCreator(String personId);
}
