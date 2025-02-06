/**
 *
 */
package com.deloitte.elrr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.elrr.dto.AssociationDto;
import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.dto.CredentialDto;
import com.deloitte.elrr.dto.EmailDto;
import com.deloitte.elrr.dto.LearningRecordDto;
import com.deloitte.elrr.dto.PersonDto;
import com.deloitte.elrr.dto.PersonalQualificationDto;
import com.deloitte.elrr.dto.PhoneDto;
import com.deloitte.elrr.entity.Association;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Credential;
import com.deloitte.elrr.entity.Email;
import com.deloitte.elrr.entity.LearningRecord;
import com.deloitte.elrr.entity.LearningResource;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.PersonalCompetency;
import com.deloitte.elrr.entity.PersonalCredential;
import com.deloitte.elrr.entity.Phone;
import com.deloitte.elrr.exception.ResourceNotFoundException;
import com.deloitte.elrr.jpa.svc.AssociationSvc;
import com.deloitte.elrr.jpa.svc.CompetencySvc;
import com.deloitte.elrr.jpa.svc.CredentialSvc;
import com.deloitte.elrr.jpa.svc.EmailSvc;
import com.deloitte.elrr.jpa.svc.LearningRecordSvc;
import com.deloitte.elrr.jpa.svc.LearningResourceSvc;
import com.deloitte.elrr.jpa.svc.OrganizationSvc;
import com.deloitte.elrr.jpa.svc.PersonSvc;
import com.deloitte.elrr.jpa.svc.PersonalCompetencySvc;
import com.deloitte.elrr.jpa.svc.PersonalCredentialSvc;
import com.deloitte.elrr.jpa.svc.PhoneSvc;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mnelakurti
 *
 */
@CrossOrigin(origins = {
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:3001",
        "http://ec2-18-116-20-188.us-east-2.compute.amazonaws.com:5000" })
@RestController
@RequestMapping("api")
@Slf4j
public class PersonController {

    @Autowired
    private PersonSvc personSvc;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PhoneSvc phoneSvc;

    @Autowired
    private EmailSvc emailSvc;

    /**
     *
     * @param personId
     * @return ResponseEntity<List<PersonDto>>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/person")
    public ResponseEntity<List<PersonDto>> getAllPersons(
            @RequestParam(value = "id", required = false) final UUID personId)
            throws ResourceNotFoundException {
        try {
            log.info("getting  PersonDto:.........");
            log.info("getting Person id:........." + personId);
            List<PersonDto> persontoList = new ArrayList<>();
            if (personId == null) {
                Iterable<Person> persons = personSvc.findAll();

                for (Person person : persons) {
                    PersonDto personDto = mapper.map(person, PersonDto.class);
                    persontoList.add(personDto);
                }
            } else {
                Person person = personSvc.get(personId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Person not found for this id :: " + personId));
                PersonDto personDto = mapper.map(person, PersonDto.class);
                persontoList.add(personDto);

            }

            if (persontoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(persontoList);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param personId
     * @return ResponseEntity<PersonDto>
     * @throws ResourceNotFoundException
     */
    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDto> getPersonById(
            @PathVariable(value = "id") final UUID personId)
            throws ResourceNotFoundException {
        log.info("getting  Person:.........");
        log.info("getting Person id:........." + personId);
        Person person = personSvc.get(personId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person not found for this id :: " + personId));
        PersonDto personDto = mapper.map(person, PersonDto.class);
        return ResponseEntity.ok().body(personDto);
    }

    /**
     *
     * @param personDto
     * @return ResponseEntity<PersonDto>
     */
    @PostMapping("/person")
    public ResponseEntity<PersonDto> createPerson(
            @Valid @RequestBody final PersonDto personDto) {
        Person person = mapper.map(personDto, Person.class);
        PersonDto response = mapper.map(personSvc.save(person), PersonDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     *
     * @param personId
     * @param personDto
     * @return ResponseEntity<PersonDto>
     * @throws ResourceNotFoundException
     */
    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDto> updatePerson(
            @PathVariable(value = "id") final UUID personId,
            @Valid @RequestBody final PersonDto personDto)
            throws ResourceNotFoundException {
        log.info("Updating  personId:.........");
        log.info("Updating personId id:........." + personId);
        Person person = personSvc.get(personId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person not found for this id to update :: "
                                + personId));
        log.info("Update Person:........." + personDto);
        // Assigning values from request
        mapper.map(personDto, person);
        // Reset Id / Primary key from query parameter
        person.setId(personId);
        log.info("Update Person:........." + person);
        return ResponseEntity
                .ok(mapper.map(personSvc.save(person), PersonDto.class));

    }

    /**
     *
     * @param personId
     * @return ResponseEntity<HttpStatus>
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/person/{id}")
    public ResponseEntity<HttpStatus> deletePerson(
            @PathVariable(value = "id") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Deleting  Person:.........");
        log.info("Deleting Person id:........." + personId);
        personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id to delete :: " + personId));
        personSvc.delete(personId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /*
     * Phones
     */

    @GetMapping("/person/{personId}/phone")
    public ResponseEntity<List<PhoneDto>> getPhones(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting phones for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        return ResponseEntity.ok(person.getPhoneNumbers().stream()
                .map(p -> mapper.map(p, PhoneDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/person/{personId}/phone")
    public ResponseEntity<List<PhoneDto>> addPhoneToPerson(
            @PathVariable(value = "personId") final UUID personId,
            @Valid @RequestBody final PhoneDto phoneDto)
            throws ResourceNotFoundException {
        log.info("Adding Phone to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Phone phone = phoneSvc.save(mapper.map(phoneDto, Phone.class));
        person.getPhoneNumbers().add(phone);
        personSvc.save(person);
        return ResponseEntity.ok(person.getPhoneNumbers().stream()
                .map(p -> mapper.map(p, PhoneDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/person/{personId}/phone/{phoneId}")
    public ResponseEntity<List<PhoneDto>> associatePhoneWithPerson(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "phoneId") final UUID phoneId)
            throws ResourceNotFoundException {
        log.info("Removing Phone(id: " + phoneId + ") from Person with id: " + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Phone phone = phoneSvc.get(phoneId).orElseThrow(() -> new ResourceNotFoundException(
                "Phone not found for this id :: " + personId));
        person.getPhoneNumbers().add(phone);
        personSvc.save(person);
        return ResponseEntity.ok(person.getPhoneNumbers().stream()
                .map(p -> mapper.map(p, PhoneDto.class))
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/person/{personId}/phone/{phoneId}")
    public ResponseEntity<HttpStatus> removePhoneFromPerson(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "phoneId") final UUID phoneId)
            throws ResourceNotFoundException {
        log.info("Removing Phone(id: " + phoneId + ") from Person with id: " + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        person.setPhoneNumbers(person.getPhoneNumbers().stream()
                .filter(p -> !p.getId().equals(phoneId))
                .collect(Collectors.toSet()));
        personSvc.save(person);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /*
     * Emails
     */

    @GetMapping("/person/{personId}/email")
    public ResponseEntity<List<EmailDto>> getEmails(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting emails for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        return ResponseEntity.ok(person.getEmailAddresses().stream()
                .map(p -> mapper.map(p, EmailDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/person/{personId}/email")
    public ResponseEntity<List<EmailDto>> addEmailToPerson(
            @PathVariable(value = "personId") final UUID personId,
            @Valid @RequestBody final EmailDto emailDto)
            throws ResourceNotFoundException {
        log.info("Adding Email to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Email email = emailSvc.save(mapper.map(emailDto, Email.class));
        person.getEmailAddresses().add(email);
        personSvc.save(person);
        return ResponseEntity.ok(person.getEmailAddresses().stream()
                .map(p -> mapper.map(p, EmailDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/person/{personId}/email/{emailId}")
    public ResponseEntity<List<EmailDto>> associateEmailWithPerson(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "emailId") final UUID emailId)
            throws ResourceNotFoundException {
        log.info("Removing Email(id: " + emailId + ") from Person with id: " + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Email email = emailSvc.get(emailId).orElseThrow(() -> new ResourceNotFoundException(
                "Email not found for this id :: " + personId));
        person.getEmailAddresses().add(email);
        personSvc.save(person);
        return ResponseEntity.ok(person.getEmailAddresses().stream()
                .map(p -> mapper.map(p, EmailDto.class))
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/person/{personId}/email/{emailId}")
    public ResponseEntity<HttpStatus> removeEmailFromPerson(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "emailId") final UUID emailId)
            throws ResourceNotFoundException {
        log.info("Removing Email(id: " + emailId + ") from Person with id: " + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        person.setEmailAddresses(person.getEmailAddresses().stream()
                .filter(p -> !p.getId().equals(emailId))
                .collect(Collectors.toSet()));
        personSvc.save(person);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /**
     * COMPETENCY
     */

    @Autowired
    private CompetencySvc compSvc;

    @Autowired
    private PersonalCompetencySvc personalCompetencySvc;

    @GetMapping("/person/{personId}/competency")
    public ResponseEntity<List<PersonalQualificationDto<CompetencyDto>>> getCompetencies(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting competencies for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));

        return ResponseEntity.ok(person.getCompetencies().stream()
                .map(c -> new PersonalQualificationDto<CompetencyDto>(
                        mapper.map(c.getCompetency(), CompetencyDto.class),
                        c.getHasRecord()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/person/{personId}/competency/{competencyId}")
    public ResponseEntity<PersonalQualificationDto<CompetencyDto>> getCompetency(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "competencyId") final UUID competencyId)
            throws ResourceNotFoundException {
        log.info("Getting competencies for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        PersonalCompetency pc = person.getCompetencies().stream()
                .filter(c -> c.getCompetency().getId().equals(competencyId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Competency not found this id :: " + personId));
        return ResponseEntity.ok(new PersonalQualificationDto<CompetencyDto>(
                mapper.map(pc.getCompetency(), CompetencyDto.class),
                pc.getHasRecord()));
    }

    @PostMapping("/person/{personId}/competency/{competencyId}")
    public ResponseEntity<List<PersonalQualificationDto<CompetencyDto>>> associateCompetency(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "competencyId") final UUID competencyId,
            @Valid @RequestBody final PersonalQualificationDto<CompetencyDto> compDto)
            throws ResourceNotFoundException {
        log.info("Adding Competency to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Competency comp = compSvc.get(competencyId).orElseThrow(() -> new ResourceNotFoundException(
                "Competency not found for this id :: " + competencyId));
        PersonalCompetency pc = (PersonalCompetency) personalCompetencySvc.save(
                new PersonalCompetency(person, comp, compDto.getHasRecord()));
        person.getCompetencies().add(pc);
        personSvc.save(person);
        return ResponseEntity.ok(person.getCompetencies().stream()
                .map(c -> new PersonalQualificationDto<CompetencyDto>(
                        mapper.map(c.getCompetency(), CompetencyDto.class),
                        c.getHasRecord()))
                .collect(Collectors.toList()));
    }

    @PutMapping("/person/{personId}/competency/{competencyId}")
    public ResponseEntity<List<PersonalQualificationDto<CompetencyDto>>> updateCompetencyAssociation(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "competencyId") final UUID competencyId,
            @Valid @RequestBody final PersonalQualificationDto<CompetencyDto> compDto)
            throws ResourceNotFoundException {
        log.info("Adding Competency to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        PersonalCompetency pc = person.getCompetencies().stream()
                .filter(c -> c.getCompetency().getId().equals(competencyId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Competency not found this id :: " + personId));
        pc.setHasRecord(compDto.getHasRecord());
        personalCompetencySvc.save(pc);
        return ResponseEntity.ok(person.getCompetencies().stream()
                .map(c -> new PersonalQualificationDto<CompetencyDto>(
                        mapper.map(c.getCompetency(), CompetencyDto.class),
                        c.getHasRecord()))
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/person/{personId}/competency/{competencyId}")
    public ResponseEntity<HttpStatus> deleteCompetencyAssociation(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "competencyId") final UUID competencyId)
            throws ResourceNotFoundException {
        log.info("Adding Competency to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        PersonalCompetency pc = person.getCompetencies().stream()
                .filter(c -> c.getCompetency().getId().equals(competencyId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person->Competency not found for this competency id :: " + personId));
        person.getCompetencies().remove(pc);
        personalCompetencySvc.delete(pc.getId());
        personSvc.save(person);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /**
     * CREDENTIAL
     */

    @Autowired
    private CredentialSvc credentialSvc;

    @Autowired
    private PersonalCredentialSvc personalCredentialSvc;

    @GetMapping("/person/{personId}/credential")
    public ResponseEntity<List<PersonalQualificationDto<CredentialDto>>> getCredentials(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting competencies for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));

        return ResponseEntity.ok(person.getCredentials().stream()
                .map(c -> new PersonalQualificationDto<CredentialDto>(
                        mapper.map(c.getCredential(), CredentialDto.class),
                        c.getHasRecord()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/person/{personId}/credential/{credentialId}")
    public ResponseEntity<PersonalQualificationDto<CredentialDto>> getCredential(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "credentialId") final UUID credentialId)
            throws ResourceNotFoundException {
        log.info("Getting competencies for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        PersonalCredential pc = person.getCredentials().stream()
                .filter(c -> c.getCredential().getId().equals(credentialId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Credential not found this id :: " + personId));
        return ResponseEntity.ok(new PersonalQualificationDto<CredentialDto>(
                mapper.map(pc.getCredential(), CredentialDto.class),
                pc.getHasRecord()));
    }

    @PostMapping("/person/{personId}/credential/{credentialId}")
    public ResponseEntity<List<PersonalQualificationDto<CredentialDto>>> associateCredential(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "credentialId") final UUID credentialId,
            @Valid @RequestBody final PersonalQualificationDto<CredentialDto> compDto)
            throws ResourceNotFoundException {
        log.info("Adding Credential to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Credential comp = credentialSvc.get(credentialId).orElseThrow(() -> new ResourceNotFoundException(
                "Credential not found for this id :: " + credentialId));
        PersonalCredential pc = (PersonalCredential) personalCredentialSvc.save(
                new PersonalCredential(person, comp, compDto.getHasRecord()));
        person.getCredentials().add(pc);
        personSvc.save(person);
        return ResponseEntity.ok(person.getCredentials().stream()
                .map(c -> new PersonalQualificationDto<CredentialDto>(
                        mapper.map(c.getCredential(), CredentialDto.class),
                        c.getHasRecord()))
                .collect(Collectors.toList()));
    }

    @PutMapping("/person/{personId}/credential/{credentialId}")
    public ResponseEntity<List<PersonalQualificationDto<CredentialDto>>> updateCredentialAssociation(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "credentialId") final UUID credentialId,
            @Valid @RequestBody final PersonalQualificationDto<CredentialDto> compDto)
            throws ResourceNotFoundException {
        log.info("Adding Credential to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        PersonalCredential pc = person.getCredentials().stream()
                .filter(c -> c.getCredential().getId().equals(credentialId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Credential not found this id :: " + personId));
        pc.setHasRecord(compDto.getHasRecord());
        personalCredentialSvc.save(pc);
        return ResponseEntity.ok(person.getCredentials().stream()
                .map(c -> new PersonalQualificationDto<CredentialDto>(
                        mapper.map(c.getCredential(), CredentialDto.class),
                        c.getHasRecord()))
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/person/{personId}/credential/{credentialId}")
    public ResponseEntity<HttpStatus> deleteCredentialAssociation(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "credentialId") final UUID credentialId)
            throws ResourceNotFoundException {
        log.info("Adding Credential to Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        PersonalCredential pc = person.getCredentials().stream()
                .filter(c -> c.getCredential().getId().equals(credentialId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Person->Credential not found for this credential id :: " + personId));
        person.getCredentials().remove(pc);
        personalCredentialSvc.delete(pc.getId());
        personSvc.save(person);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /**
     * LEARNING RECORD
     */

    @Autowired
    private LearningResourceSvc learningResourceSvc;

    @Autowired
    private LearningRecordSvc learningRecordSvc;

    @GetMapping("/person/{personId}/learningrecord")
    public ResponseEntity<List<LearningRecordDto>> getLearningRecords(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting competencies for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));

        return ResponseEntity.ok(person.getLearningRecords().stream()
                .map(record -> mapper.map(record, LearningRecordDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/person/{personId}/learningrecord")
    public ResponseEntity<List<LearningRecordDto>> associateCredential(
            @PathVariable(value = "personId") final UUID personId,
            @Valid @RequestBody final LearningRecordDto learningRecordDto)
            throws ResourceNotFoundException {
        log.info("Adding Credential to Person with id:......" + personId);

        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        UUID learningResourceId = learningRecordDto.getLearningResource().getId();
        LearningResource resource = learningResourceSvc.get(learningResourceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Learning Resource not found for this id :: " + learningResourceId));
        LearningRecord record = mapper.map(learningRecordDto, LearningRecord.class);
        record.setPerson(person);
        record.setLearningResource(resource);
        learningRecordSvc.save(record);
        person.getLearningRecords().add(record);
        personSvc.save(person);
        return ResponseEntity.ok(person.getLearningRecords().stream()
                .map(rec -> mapper.map(rec, LearningRecordDto.class))
                .collect(Collectors.toList()));
    }

    /**
     * ORGANIZATION
     */

    @Autowired
    private OrganizationSvc organizationSvc;

    @Autowired
    private AssociationSvc associationSvc;

    @GetMapping("/person/{personId}/organization")
    public ResponseEntity<List<AssociationDto>> getOrganizationsByPerson(
            @PathVariable(value = "personId") final UUID personId)
            throws ResourceNotFoundException {
        log.info("Getting orgs for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));

        return ResponseEntity.ok(person.getAssociations().stream()
                .map(assoc -> mapper.map(assoc, AssociationDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/person/{personId}/organization/{organizationId}")
    public ResponseEntity<AssociationDto> getOrganizationsByPerson(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "organizationId") final UUID organizationId)
            throws ResourceNotFoundException {
        log.info("Getting orgs for Person with id:......" + personId);
        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Association association = person.getAssociations().stream()
                .filter(assoc -> assoc.getOrganization().getId().equals(organizationId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Association Exists for orgId::" + organizationId));
        return ResponseEntity.ok(mapper.map(association, AssociationDto.class));
    }

    @PostMapping("/person/{personId}/organization/{organizationId}")
    public ResponseEntity<List<AssociationDto>> associateOrg(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "organizationId") final UUID organizationId,
            @Valid @RequestBody final AssociationDto associationDto)
            throws ResourceNotFoundException {
        log.info("Adding Organization to Person with id:......" + personId);

        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        Organization organization = organizationSvc.get(organizationId).orElseThrow(
                () -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));

        Association association = person.getAssociations().stream()
                .filter(assoc -> assoc.getOrganization().getId().equals(organizationId))
                .findAny()
                .orElse(new Association());
        association.setOrganization(organization);
        association.setPerson(person);
        association.setAssociationType(associationDto.getAssociationType());

        person.getAssociations().add(association);

        associationSvc.save(association);
        personSvc.save(person);
        return ResponseEntity.ok(person.getAssociations().stream()
                .map(assoc -> mapper.map(assoc, AssociationDto.class))
                .collect(Collectors.toList()));
    }

    @PutMapping("/person/{personId}/organization/{organizationId}")
    public ResponseEntity<List<AssociationDto>> updateOrgAssociation(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "organizationId") final UUID organizationId,
            @Valid @RequestBody final AssociationDto associationDto)
            throws ResourceNotFoundException {
        log.info("Updating Organization association to Person with id:......" + personId);

        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));
        organizationSvc.get(organizationId).orElseThrow(
                () -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));

        Association association = person.getAssociations().stream()
                .filter(assoc -> assoc.getOrganization().getId().equals(organizationId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No association found for org"));
        association.setAssociationType(associationDto.getAssociationType());

        associationSvc.save(association);
        personSvc.save(person);
        return ResponseEntity.ok(person.getAssociations().stream()
                .map(assoc -> mapper.map(assoc, AssociationDto.class))
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/person/{personId}/organization/{organizationId}")
    public ResponseEntity<HttpStatus> deleteOrgAssociation(
            @PathVariable(value = "personId") final UUID personId,
            @PathVariable(value = "organizationId") final UUID organizationId)
            throws ResourceNotFoundException {
        log.info("Updating Organization association to Person with id:......" + personId);

        Person person = personSvc.get(personId).orElseThrow(() -> new ResourceNotFoundException(
                "Person not found for this id :: " + personId));

        Association association = person.getAssociations().stream()
                .filter(assoc -> assoc.getOrganization().getId().equals(organizationId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No association found for org"));
        person.getAssociations().remove(association);
        associationSvc.delete(association.getId());
        personSvc.save(person);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
