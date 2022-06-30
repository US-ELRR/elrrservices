/**
 *
 */
package com.deloitte.elrr.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.Employment;
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

/**
 * @author mnelakurti
 *
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LearnerCreatorImplTest {

    /**
    *
    */
    @Mock
    private CourseRepository courseRepository;
    /**
    *
    */
    @Mock
    private CompetencyRepository competencyRepository;
    /**
    *
    */
    @Mock
    private PersonalRepository personalRepository;
    /**
    *
    */
    @Mock
    private EmploymentRepository employmentRepository;
    /**
    *
    */
    @Mock
    private OrganizationRepository organizationRepository;
    /**
    *
    */
    @Mock
    private LearnerProfileRepository learnerProfileRepository;
    /**
    *
    */
    @Mock
    private ContactInformationRepository contactInformationRepository;

    /**
    *
    *
    */
    @Test
    void test() {
        LearnerCreatorImpl learnerCreatorImpl = new LearnerCreatorImpl();
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "courseRepository",
                courseRepository);
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "competencyRepository",
                competencyRepository);
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "personalRepository",
                personalRepository);
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "employmentRepository",
                employmentRepository);
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "organizationRepository", organizationRepository);
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "learnerProfileRepository", learnerProfileRepository);
        ReflectionTestUtils.setField(learnerCreatorImpl,
                "contactInformationRepository",
                contactInformationRepository);
        Mockito.doReturn(getLearnerProfileList())
        .when(learnerProfileRepository)
                .findAll();
          Competency competency = new Competency();
          Mockito.doReturn(Optional.of(competency)) .when(competencyRepository)
          .findById(1L);
          Course course = new Course();
          Mockito.doReturn(Optional.of(course))
          .when(courseRepository).findById(1L);
          Person person = new Person();
          Mockito.doReturn(Optional.of(person))
          .when(personalRepository).findById(1L);
          Organization organization = new Organization();
          Mockito.doReturn(Optional.of(organization))
          .when(organizationRepository) .findById(1L);
        Mockito.doReturn(getContactInformationList())
                .when(contactInformationRepository).findAll();
        Employment employment = new Employment();
        Mockito.doReturn(Optional.of(employment))
        .when(employmentRepository)
                .findById(Mockito.any());
        learnerCreatorImpl.learnerCreator("1");
    }

    /**
     *
     * @return Iterable<CompetencyDto>
     */
    private static List<ContactInformation> getContactInformationList() {
        List<ContactInformation> contactInformationList = new ArrayList<>();
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setContactinformationid(1L);
        contactInformationList.add(contactInformation);

        return contactInformationList;
    }

    /**
     *
     * @return Iterable<CourseDto>
     */
    private static List<LearnerProfile> getLearnerProfileList() {
        List<LearnerProfile> learnerProfileList = new ArrayList<>();
        LearnerProfile learnerProfile = new LearnerProfile();
        learnerProfile.setLearnerprofileid(1L);
        learnerProfile.setPersonid(1L);
        learnerProfile.setCourseid(1L);
        learnerProfile.setCompetencyid(1L);
        learnerProfile.setOrganizationid(1L);
        learnerProfileList.add(learnerProfile);
        return learnerProfileList;
    }

    /**
     *
     * @return Iterable<CourseDto>
     */
    private static Personnel getPersonnelList() {
        Personnel personnel = new Personnel();
        Person person = new Person();
        person.setPersonid(1L);
        personnel.setPerson(person);
        return personnel;
    }
}
