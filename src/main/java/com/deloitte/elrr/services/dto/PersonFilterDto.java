package com.deloitte.elrr.services.dto;

import java.util.UUID;

import lombok.Data;

/**
 * DTO for filtering persons in search queries.
 * All fields are optional and correspond to query parameters.
 */
@Data
public class PersonFilterDto {

    /**
     * Optional person ID filter.
     */
    private UUID[] id;

    /**
     * Optional IFI (Inverse Functional Identifier) filter.
     */
    private String[] ifi;

    /**
     * Optional associated organization ID filter.
     */
    private UUID[] associatedOrgId;

    /**
     * Optional employer organization ID filter.
     */
    private UUID[] employerOrgId;

    /**
     * Optional filter for extension keys.
     */
    private String[] hasExtension;

    /**
     * Optional filter for extension JSONPath expressions.
     */
    private String[] extensionPath;

    /**
     * Optional filter for extension JSONPath predicate expressions.
     */
    private String[] extensionPathMatch;

    /**
     * Optional filter for person names.
     */
    private String[] name;

    /**
     * Optional location ID filter for any location field.
     */
    private UUID[] locationId;

    /**
     * Optional filter for email addresses.
     */
    private String[] emailAddress;

    /**
     * Optional filter for phone numbers (normalized search).
     */
    private String[] phoneNumber;

    /**
     * Optional competency ID filter.
     */
    private UUID[] competencyId;

    /**
     * Optional credential ID filter.
     */
    private UUID[] credentialId;

    /**
     * Optional learning resource ID filter.
     */
    private String[] learningResourceId;
}
