package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.UUID;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class LocationDto implements Serializable {

    private static final long serialVersionUID = -8031955138252824918L;

    private UUID id;
    
    @Size(max = 255)
    private String streetNumberAndName;

    @Size(max = 255)
    private String apartmentRoomSuiteNumber;
    
    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String stateAbbreviation;

    @Size(max = 255)
    private String postalCode;

    @Size(max = 255)
    private String county;

    @Size(max = 255)
    private String countryCode;
    
    @Size(max = 255)
    private String latitude;

    @Size(max = 255)
    private String longitude;

}
