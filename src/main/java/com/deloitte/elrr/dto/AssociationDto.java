package com.deloitte.elrr.dto;

import com.deloitte.elrr.entity.types.AssociationType;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AssociationDto extends AbstractDto {

    @Valid
    private OrganizationDto organization;

    private AssociationType associationType;

}
