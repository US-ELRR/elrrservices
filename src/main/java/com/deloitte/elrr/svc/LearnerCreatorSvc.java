package com.deloitte.elrr.svc;

import java.util.Date;

import com.deloitte.elrr.dto.LearnerDTO;
import com.deloitte.elrr.dto.LearnerSnapshotDTO;
import com.deloitte.elrr.dto.LearnersDTO;

public interface LearnerCreatorSvc {

	public LearnerDTO learnerCreator(String personId);

	public LearnersDTO getLearners(Date startDate, Date endDate, String orgName, String[] responseEntities,
			int startRecord);

	public LearnerSnapshotDTO getSnapshot();
}
