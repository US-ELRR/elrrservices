package com.deloitte.elrr.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MilitaryRecordDto extends AbstractDto {

    @Size(max = 100)
    @NotNull
    private String branch;

    @Size(max = 100)
    @NotNull
    private String country;

    private LocalDate inductionDate;

    @Size(max = 100)
    private String inductionRank;

    private LocalDate releaseDate;

    @Size(max = 100)
    private String currentRank;

    @Size(max = 100)
    private String currentStatus;

    private LocalDate dischargeDate;

    @Size(max = 100)
    private String dischargeCategory;

    @Size(max = 100)
    private String dischargeRank;

    @Size(max = 100)
    private String highestRank;

    @Size(max = 100)
    private String militaryId;

}
