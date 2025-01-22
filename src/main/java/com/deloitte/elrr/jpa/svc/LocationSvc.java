package com.deloitte.elrr.jpa.svc;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Location;
import com.deloitte.elrr.repository.LocationRepository;

@Service
public class LocationSvc implements CommonSvc<Location, UUID> {
    /**
     *
     */
    private final LocationRepository locationRepository;
    /**
     *
     * @param argsLocationRepository
     */
    public LocationSvc(
            final LocationRepository argsLocationRepository) {
        this.locationRepository = argsLocationRepository;
    }
    /**
     *
     */
    @Override
    public CrudRepository<Location, UUID> getRepository() {
        return this.locationRepository;
    }
    /**
     *
     */
    @Override
    public UUID getId(final Location location) {
        return location.getId();
    }
    /**
     *
     */
    @Override
    public Location save(final Location location) {
        return CommonSvc.super.save(location);
    }

}
