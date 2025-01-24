package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Credential;
import com.deloitte.elrr.repository.CredentialRepository;

@Service
public class CredentialSvc implements CommonSvc<Credential, UUID> {
    /**
     *
     */
    private final CredentialRepository credentialRepository;
    /**
     *
     * @param argsCredentialRepository
     */
    public CredentialSvc(
            final CredentialRepository argsCredentialRepository) {
        this.credentialRepository = argsCredentialRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Credential, UUID> getRepository() {
        return this.credentialRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Credential credential) {
        return credential.getId();
    }
    /**
     *
     */
    @Override
    public Credential save(final Credential credential) {
        return CommonSvc.super.save(credential);
    }

}
