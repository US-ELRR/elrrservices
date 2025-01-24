package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Facility;
import com.deloitte.elrr.repository.FacilityRepository;

@Service
public class FacilitySvc implements CommonSvc<Facility, UUID> {
    
    private final FacilityRepository facilityRepository;

    @Autowired
    private LocationSvc locationSvc;

    /**
     *
     * @param argsFacilityRepository
     */
    public FacilitySvc(
            final FacilityRepository argsFacilityRepository) {
        this.facilityRepository = argsFacilityRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Facility, UUID> getRepository() {
        return this.facilityRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Facility facility) {
        return facility.getId();
    }
    /**
     *
     */
    @Override
    public Facility save(final Facility facility) {
        if (facility.getLocation() != null)
            facility.setLocation(locationSvc.save(facility.getLocation()));
            
        return CommonSvc.super.save(facility);
    }

}
