package com.deloitte.elrr.svc;

import java.util.Date;

import com.deloitte.elrr.dto.LearnerDTO;
import com.deloitte.elrr.dto.LearnerSnapshotDTO;
import com.deloitte.elrr.dto.LearnersDTO;
import com.deloitte.elrr.entity.Learner;

public interface LearnerCreatorSvc {

	public LearnerDTO learnerCreator(String personId);
	
	public LearnersDTO getLearners(Date startDate, Date endDate, String orgName, String[] responseEntities, int StartRecord);

	public LearnerSnapshotDTO getSnapshot();  
}
