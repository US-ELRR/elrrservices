package com.deloitte.elrr.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class FacilityDto extends AbstractDto {
    
    
    @Size(max = 255)
    private String name;

    private String description;

    @Size(max = 255)
    private String operational_status;

    @Size(max = 255)
    private String facility_security_level;

    @Valid
    private LocationDto location;
}
