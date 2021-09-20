/**
 * 
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Accreditation;
import com.deloitte.elrr.repository.AccreditationRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class AccreditationSvc implements CommonSvc<Accreditation, Long> {
	private final AccreditationRepository accreditationRepository;

	public AccreditationSvc(final AccreditationRepository accreditationRepository) {
		this.accreditationRepository = accreditationRepository;
	}

	@Override
	public CrudRepository<Accreditation, Long> getRepository() {
		return this.accreditationRepository;
	}

	@Override
	public Long getId(Accreditation accreditation) {
		return accreditation.getAccreditationid();
	}

	@Override
	public Accreditation save(Accreditation accreditation) {
		return CommonSvc.super.save(accreditation);
	}

}
