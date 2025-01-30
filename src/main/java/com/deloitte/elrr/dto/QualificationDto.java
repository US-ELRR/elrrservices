package com.deloitte.elrr.dto;

import java.util.Date;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class QualificationDto extends AbstractDto {

    @Size(max = 100)
    private String identifier;

    private String identifierUrl;

    @Size(max = 100)
    private String code;

    @Size(max = 100)
    private String taxonomyId;

    private Date validStartDate;

    private Date validEndDate;

    @Size(max = 100)
    private String parentId;

    private String parentUrl;

    @Size(max = 100)
    private String parentCode;

    private String typeUrl;

    private String statement;

    @Size(max = 100)
    private String frameworkTitle;

    @Size(max = 100)
    private String frameworkVersion;

    @Size(max = 100)
    private String frameworkIdentifier;

    private String frameworkDescription;

    @Size(max = 100)
    private String frameworkSubject;

    private Date frameworkValidStartDate;

    private Date frameworkValidEndDate;

    @Size(max = 10)
    private String recordStatus;
}
