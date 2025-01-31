package com.deloitte.elrr.dto;

import java.time.LocalDateTime;
import java.util.Date;

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
public class LearningRecordDto extends AbstractDto {

    @Valid
    private LearningResourceDto learningResource;
    
    private Date enrollmentDate;

    private LearningStatus recordStatus;

    @Size(max = 50)
    private String academicGrade;

    private LocalDateTime eventTime;

}
