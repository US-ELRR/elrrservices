package com.deloitte.elrr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompetencyDto extends QualificationDto {
    private final String type = "COMPETENCY";
}
