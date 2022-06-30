package com.deloitte.elrr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.elrr.entity.ContactInformation;

@Repository
public interface ContactInformationRepository
        extends JpaRepository<ContactInformation, Long> {
    /**
     *
     * @param electronicmailaddress
     * @return ContactInformation
     */
    ContactInformation findIdByElectronicmailaddress(
            String electronicmailaddress);

}
