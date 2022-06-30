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
    /**
     *
     */
    private final AccreditationRepository accreditationRepository;
    /**
     *
     * @param argsAccreditationRepository
     */
    public AccreditationSvc(
            final AccreditationRepository argsAccreditationRepository) {
        this.accreditationRepository = argsAccreditationRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Accreditation, Long> getRepository() {
        return this.accreditationRepository;
    }
    /**
     *
     */
    @Override
    public Long getI(final Accreditation accreditation) {
        return accreditation.getAccreditationid();
    }
    /**
     *
     */
    @Override
    public Accreditation save(final Accreditation accreditation) {
        return CommonSvc.super.save(accreditation);
    }

}
