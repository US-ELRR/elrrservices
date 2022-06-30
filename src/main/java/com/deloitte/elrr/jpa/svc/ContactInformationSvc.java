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
public class ContactInformationSvc
        implements CommonSvc<ContactInformation, Long> {
    /**
     *
     */
    private final ContactInformationRepository contactInformationRepository;
    /**
     *
     * @param argsContactInformationRepository
     */
    public ContactInformationSvc(
         final ContactInformationRepository argsContactInformationRepository) {
        this.contactInformationRepository = argsContactInformationRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<ContactInformation, Long> getRepository() {
        return this.contactInformationRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final ContactInformation courseaccreditation) {
        return courseaccreditation.getContactinformationid();
    }
    /**
     *
     */
    @Override
    public ContactInformation save(
            final ContactInformation contactInformation) {
        return CommonSvc.super.save(contactInformation);
    }
    /**
     *
     * @param courseidentifier
     * @return ContactInformation
     */
    public ContactInformation getContactInformationByElectronicmailaddress(
            final String courseidentifier) {
        return this.contactInformationRepository
                .findIdByElectronicmailaddress(courseidentifier);
    }

}
