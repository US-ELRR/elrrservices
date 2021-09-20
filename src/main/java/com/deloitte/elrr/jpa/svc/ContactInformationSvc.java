/**
 * 
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.repository.ContactInformationRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class ContactInformationSvc implements CommonSvc<ContactInformation, Long> {
	private final ContactInformationRepository contactInformationRepository;

	public ContactInformationSvc(final ContactInformationRepository contactInformationRepository) {
		this.contactInformationRepository = contactInformationRepository;
	}

	@Override
	public CrudRepository<ContactInformation, Long> getRepository() {
		return this.contactInformationRepository;
	}

	@Override
	public Long getId(ContactInformation courseaccreditation) {
		return courseaccreditation.getContactinformationid();
	}

	@Override
	public ContactInformation save(ContactInformation contactInformation) {
		return CommonSvc.super.save(contactInformation);
	}

	public ContactInformation getContactInformationByElectronicmailaddress(String courseidentifier) {
		return this.contactInformationRepository.findIdByElectronicmailaddress(courseidentifier);
	}

}
