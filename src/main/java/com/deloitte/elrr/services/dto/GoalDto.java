package com.deloitte.elrr.services.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import com.deloitte.elrr.entity.types.GoalType;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GoalDto extends ExtensibleDto {

    private UUID personId;

    @NotNull
    private GoalType type;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 65535)
    private String description;

    private LocalDate startDate;

    private LocalDate achievedByDate;

    private LocalDate expirationDate;

    private Set<UUID> competencyIds;

    private Set<UUID> credentialIds;

    private Set<UUID> learningResourceIds;
}
