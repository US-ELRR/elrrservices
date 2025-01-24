package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Email;
import com.deloitte.elrr.repository.EmailRepository;

@Service
public class EmailSvc implements CommonSvc<Email, UUID> {
    /**
     *
     */
    private final EmailRepository emailRepository;
    /**
     *
     * @param argsEmailRepository
     */
    public EmailSvc(
            final EmailRepository argsEmailRepository) {
        this.emailRepository = argsEmailRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Email, UUID> getRepository() {
        return this.emailRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Email email) {
        return email.getId();
    }
    /**
     *
     */
    @Override
    public Email save(final Email email) {
        return CommonSvc.super.save(email);
    }

}
