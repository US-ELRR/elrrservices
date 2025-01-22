package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class PersonDto implements Serializable {

    private static final long serialVersionUID = -8031955138252824918L;

    private UUID id;
  
    @Size(max = 255)
    private String name;
    
    @Size(max = 255)
    private String firstName;
    
    @Size(max = 255)
    private String middleName;
    
    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String namePrefix;

    @Size(max = 255)
    private String titleAffixCode;

    @Size(max = 255)
    private String nameSuffix;

    @Size(max = 255)
    private String qualificationAffixCode;

    @Size(max = 255)
    private String maidenName;

    private Date birthdate;

    @Size(max = 255)
    private String citizenship;

    private BigDecimal height;
    
    @Size(max = 255)
    private String heightUnit;
    
    private BigDecimal weight;

    @Size(max = 255)
    private String weightUnit;

    private Long interpupillaryDistance;

    @Size(max = 255)
    private String handedness;

    @Size(max = 255)
    private String primaryLanguage;

    @Size(max = 255)
    private String currentSecurityClearance;

    @Size(max = 255)
    private String highestSecurityClearance;

    private Boolean unionMembership;

    @Valid
    private LocationDto mailingAddress;
}
