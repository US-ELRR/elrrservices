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
	private final LearnerProfileRepository learnerProfileFactRepository;

	public LearnerProfileSvc(final LearnerProfileRepository learnerProfileFactRepository) {
		this.learnerProfileFactRepository = learnerProfileFactRepository;
	}

	@Override
	public CrudRepository<LearnerProfile, Long> getRepository() {
		return this.learnerProfileFactRepository;
	}

	@Override
	public Long getId(LearnerProfile learnerProfileFact) {
		return learnerProfileFact.getPersonid();
		}

	@Override
	public LearnerProfile save(LearnerProfile learnerProfileFact) {
		return CommonSvc.super.save(learnerProfileFact);
	}

}
