package com.deloitte.elrr.services.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AuditableDto extends AbstractDto {

    private LocalDateTime insertedDate;

    private String updatedBy;

    private LocalDateTime lastModified;

}
