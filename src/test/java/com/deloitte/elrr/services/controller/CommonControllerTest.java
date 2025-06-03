package com.deloitte.elrr.services.controller;

import java.io.IOException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.deloitte.elrr.jpa.svc.AssociationSvc;
import com.deloitte.elrr.jpa.svc.CompetencySvc;
import com.deloitte.elrr.jpa.svc.CredentialSvc;
import com.deloitte.elrr.jpa.svc.EmailSvc;
import com.deloitte.elrr.jpa.svc.EmploymentRecordSvc;
import com.deloitte.elrr.jpa.svc.FacilitySvc;
import com.deloitte.elrr.jpa.svc.IdentitySvc;
import com.deloitte.elrr.jpa.svc.LearningRecordSvc;
import com.deloitte.elrr.jpa.svc.LearningResourceSvc;
import com.deloitte.elrr.jpa.svc.LocationSvc;
import com.deloitte.elrr.jpa.svc.MilitaryRecordSvc;
import com.deloitte.elrr.jpa.svc.OrganizationSvc;
import com.deloitte.elrr.jpa.svc.PersonSvc;
import com.deloitte.elrr.jpa.svc.PersonalCompetencySvc;
import com.deloitte.elrr.jpa.svc.PersonalCredentialSvc;
import com.deloitte.elrr.jpa.svc.PhoneSvc;
import com.deloitte.elrr.repository.OrganizationRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author mnelakurti
 *
 */
@Getter
@Setter
@NoArgsConstructor
class CommonControllerTest {

    @MockitoBean
    private OrganizationSvc organizationSvc;

    @MockitoBean
    private PersonSvc personSvc;

    @MockitoBean
    private PhoneSvc phoneSvc;

    @MockitoBean
    private EmailSvc emailSvc;

    @MockitoBean
    private IdentitySvc identitySvc;

    @MockitoBean
    private AssociationSvc associationSvc;

    @MockitoBean
    private MilitaryRecordSvc militaryRecordSvc;

    @MockitoBean
    private LocationSvc locationSvc;

    @MockitoBean
    private FacilitySvc facilitySvc;

    @MockitoBean
    private EmploymentRecordSvc employmentRecordSvc;

    @MockitoBean
    private CompetencySvc competencySvc;

    @MockitoBean
    private PersonalCompetencySvc personalCompetencySvc;

    @MockitoBean
    private CredentialSvc credentialSvc;

    @MockitoBean
    private PersonalCredentialSvc personalCredentialSvc;

    @MockitoBean
    private LearningResourceSvc learningResourceSvc;

    @MockitoBean
    private LearningRecordSvc learningRecordSvc;

    @MockitoBean
    private OrganizationRepository organizationRepository;

    /**
     *
     * @param obj
     * @return String
     * @throws JsonProcessingException
     */
    public static String asJsonString(final Object obj)
            throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(obj);
    }

    public static <T> T resultsAsObject(String results, TypeReference<T> type)
            throws StreamReadException, DatabindException, IOException {
        return (T) new ObjectMapper().readValue(results, type);
    }
}
