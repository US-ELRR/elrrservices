package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Association;
import com.deloitte.elrr.repository.AssociationRepository;

@Service
public class AssociationSvc implements CommonSvc<Association, UUID> {
    /**
     *
     */
    private final AssociationRepository associationRepository;
    /**
     *
     * @param argsAssociationRepository
     */
    public AssociationSvc(
            final AssociationRepository argsAssociationRepository) {
        this.associationRepository = argsAssociationRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Association, UUID> getRepository() {
        return this.associationRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Association association) {
        return association.getId();
    }
    /**
     *
     */
    @Override
    public Association save(final Association association) {
        return CommonSvc.super.save(association);
    }

}
