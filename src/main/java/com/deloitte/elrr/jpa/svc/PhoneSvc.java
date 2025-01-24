package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Phone;
import com.deloitte.elrr.repository.PhoneRepository;

@Service
public class PhoneSvc implements CommonSvc<Phone, UUID> {
    /**
     *
     */
    private final PhoneRepository phoneRepository;
    /**
     *
     * @param argsPhoneRepository
     */
    public PhoneSvc(
            final PhoneRepository argsPhoneRepository) {
        this.phoneRepository = argsPhoneRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Phone, UUID> getRepository() {
        return this.phoneRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Phone phone) {
        return phone.getId();
    }
    /**
     *
     */
    @Override
    public Phone save(final Phone phone) {
        return CommonSvc.super.save(phone);
    }

}
