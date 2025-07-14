package com.deloitte.elrr.services.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.deloitte.elrr.entity.types.LearningStatus;

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
public class LearningRecordDto extends ExtensibleDto {

    @Valid
    private LearningResourceDto learningResource;

    private LocalDate enrollmentDate;

    private LearningStatus recordStatus;

    @Size(max = 50)
    private String academicGrade;

    private LocalDateTime eventTime;

}
