package com.deloitte.elrr.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.Employment;
import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.entity.LearnerProfile;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.Personnel;
import com.deloitte.elrr.repository.CompetencyRepository;
import com.deloitte.elrr.repository.ContactInformationRepository;
import com.deloitte.elrr.repository.CourseRepository;
import com.deloitte.elrr.repository.EmploymentRepository;
import com.deloitte.elrr.repository.LearnerProfileRepository;
import com.deloitte.elrr.repository.OrganizationRepository;
import com.deloitte.elrr.repository.PersonalRepository;

@Service
public class LearnerCreatorImpl implements LearnerCreatorSvc {
    /**
     *
     */
    @Autowired
    private CourseRepository courseRepository;
    /**
     *
     */
    @Autowired
    private CompetencyRepository competencyRepository;
    /**
     *
     */
    @Autowired
    private PersonalRepository personalRepository;
    /**
     *
     */
    @Autowired
    private EmploymentRepository employmentRepository;
    /**
     *
     */
    @Autowired
    private OrganizationRepository organizationRepository;
    /**
     *
     */
    @Autowired
    private LearnerProfileRepository learnerProfileRepository;
    /**
     *
     */
    @Autowired
    private ContactInformationRepository contactInformationRepository;
    /**
     *
     * @return Learner
     */
    @Override
    public Learner learnerCreator(final String personId) {
        Learner learner = new Learner();
        List<LearnerProfile> profiles = getLearnerProfiles(personId);
        learner.setCourses(getCourses(profiles));
        learner.setCompetencies(getCompetencies(profiles));
        learner.setPersonnel(getLearnerPersonnel(personId, profiles));
        return learner;
    }
    /**
     *
     * @param profiles
     * @return List<Competency>
     */
    private List<Competency> getCompetencies(
        final List<LearnerProfile> profiles) {
        List<Competency> list = new ArrayList<>();
        for (LearnerProfile profile : profiles) {
            Optional<Competency> competency = competencyRepository
                    .findById(profile.getCompetencyid());
            list.add(competency.get());
        }
        return list;
    }
    /**
     *
     * @param profiles
     * @return List<Course>
     */
    private List<Course> getCourses(
        final List<LearnerProfile> profiles) {
        List<Course> list = new ArrayList<>();
        for (LearnerProfile profile : profiles) {
            Optional<Course> course = courseRepository
                    .findById(profile.getCourseid());
            list.add(course.get());
        }
        return list;
    }
    /**
     *
     * @param id
     * @return List<LearnerProfile>
     */
    private List<LearnerProfile> getLearnerProfiles(final String id) {
        Long personId = Long.valueOf(id);
        List<LearnerProfile> list = learnerProfileRepository.findAll();
        return list.stream()
                .filter(e -> e.getPersonid() == personId)
                .collect(Collectors.toList());
    }
    /**
     *
     * @param personId
     * @param profiles
     * @return Personnel
     */
    private Personnel getLearnerPersonnel(final String personId,
            final List<LearnerProfile> profiles) {

        Personnel personnel = new Personnel();

        Optional<Person> person = this.personalRepository
                .findById(Long.valueOf(personId));
        Optional<Organization> organization1 = this.organizationRepository
                .findById(profiles.get(0).getOrganizationid());

        personnel.setPerson(person.get());
        personnel.setOrganization(organization1.get());
        personnel.setContactInformation(getContactInformation(personId));
        personnel.setEmployment(getEmployeeList(profiles));
        return personnel;
    }
    /**
     *
     * @param id
     * @return ContactInformation
     */
    private ContactInformation getContactInformation(final String id) {
        Long personId = Long.valueOf(id);
        ContactInformation contactInformation = null;
        List<ContactInformation> list = contactInformationRepository.findAll();
        List<ContactInformation> contactList = list.stream()
                .filter(e -> e.getPersonid() == personId)
                .collect(Collectors.toList());
        if (contactList.size() > 0) {
            contactInformation = contactList.get(0);
        }
        return contactInformation;

    }
    /**
     *
     * @param profiles
     * @return List<Employment>
     */
    private List<Employment> getEmployeeList(
            final List<LearnerProfile> profiles) {
        List<Employment> list = new ArrayList<>();
        for (LearnerProfile profile : profiles) {
            Optional<Employment> employment1 = this.employmentRepository
                    .findById(profile.getEmploymentid());
            list.add(employment1.get());
        }
        return list;
    }
}
