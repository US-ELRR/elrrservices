package com.deloitte.elrr.services.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ExtensibleDto extends AuditableDto {

    private Map<String, Object> extensions;

}
