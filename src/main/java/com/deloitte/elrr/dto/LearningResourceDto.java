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
public class LearningResourceDto extends AbstractDto {

    @Size(max = 255)
    private String iri;

    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String subjectMatter;

    @Size(max = 20)
    private String subjectAbbreviation;

    @Size(max = 255)
    private String level;

    @Size(max = 255)
    private String number;

    @Size(max = 255)
    private String instructionMethod;

    private Date startDate;

    private Date endDate;

    @Size(max = 255)
    private String providerName;

    @Size(max = 255)
    private String departmentName;

    @Size(max = 255)
    private String gradeScaleCode;

    @Size(max = 255)
    private String metadataRepository;

    @Size(max = 255)
    private String lrsEndpoint;

    @Size(max = 255)
    private String description;

}
