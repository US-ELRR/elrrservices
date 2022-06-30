/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.repository.OrganizationRepository;

/**
 * @author mnelakurti
 *
 */

@Service
public class OrganizationSvc implements CommonSvc<Organization, Long> {
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
    public CrudRepository<Organization, Long> getRepository() {
        return this.organizationRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final Organization organization) {
        return organization.getOrganizationid();
    }
    /**
     *
     */
    @Override
    public Organization save(final Organization organization) {
        return CommonSvc.super.save(organization);
    }

}
