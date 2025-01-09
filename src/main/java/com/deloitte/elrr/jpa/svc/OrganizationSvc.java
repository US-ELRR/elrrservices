/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.repository.OrganizationRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class OrganizationSvc implements CommonSvc<Organization, UUID> {
    /**
     *
     */
    private final OrganizationRepository organizationRepository;
    /**
     *
     * @param argsOrganizationRepository
     */
    public OrganizationSvc(
            final OrganizationRepository argsOrganizationRepository) {
        this.organizationRepository = argsOrganizationRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Organization, UUID> getRepository() {
        return this.organizationRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Organization organization) {
        return organization.getId();
    }
    /**
     *
     */
    @Override
    public Organization save(final Organization organization) {
        return CommonSvc.super.save(organization);
    }

}
