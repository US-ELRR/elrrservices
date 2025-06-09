package com.deloitte.elrr.services.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto extends AbstractDto {
    @Size(max = 255)
    @NotBlank
    private String resource;

    private UUID resourceId;
    private List<Action> actions;

    public enum Action {
        CREATE,
        READ,
        UPDATE,
        DELETE,
        ASSOCIATE,
        DISASSOCIATE
    }
}
