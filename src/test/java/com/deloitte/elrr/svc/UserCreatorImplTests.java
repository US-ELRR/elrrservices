//package com.deloitte.elrr.svc;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.deloitte.elrr.entity.Competency;
//import com.deloitte.elrr.entity.CourseList;
//import com.deloitte.elrr.entity.Employment;
//import com.deloitte.elrr.entity.Organization;
//import com.deloitte.elrr.entity.Personal;
//import com.deloitte.elrr.entity.Personnel;
//import com.deloitte.elrr.entity.User;
//import com.deloitte.elrr.repository.CompetencyRepository;
//import com.deloitte.elrr.repository.CourseListRepository;
//import com.deloitte.elrr.repository.EmploymentRepository;
//import com.deloitte.elrr.repository.OrganizationRepository;
//import com.deloitte.elrr.repository.PersonalRepository;
//import com.deloitte.elrr.svc.LearnerCreatorImpl;
//
//@SpringBootTest
//class  UserCreatorImplTests {
//	@Mock
//	CourseListRepository courseListRepository;
//	@Mock
//	CompetencyRepository competencyListRepository;
//	@Mock
//	PersonalRepository personalRepository;
//	@Mock
//	EmploymentRepository employmentRepository;
//	@Mock
//	OrganizationRepository organizationRepository;
//
//	@Spy
//	@InjectMocks
//	LearnerCreatorImpl userCreatorImpl;
//	@Test
//	void UserCreatorImplUserCreatorTests() {
//		User user = new User();
//		User mockUser = new User();
//		List<CourseList> courseList = new ArrayList<>();
//		List<Competency> competencies = new ArrayList<>();
//		Personnel personnel = new Personnel();
//		mockUser.setCompetencyList(competencies);
//		mockUser.setCourseList(courseList);
//		mockUser.setPersonnel(personnel);
//		when(this.courseListRepository.findAll()).thenReturn(courseList);
//		when(this.competencyListRepository.findAll()).thenReturn(competencies);
//		doReturn(personnel).when(userCreatorImpl).setUserPersonnel();
//		user = userCreatorImpl.userCreator();
//		assertEquals(user.toString(), mockUser.toString());
//	}
//	
//	@Test
//	void UserCreatorImplSetUserPersonnel() {
//		Personnel personnel = new Personnel();
//		Personnel mockPersonnel = new Personnel();
//		Optional<Personal> personal = Optional.of(new Personal());
//		Optional<Organization> organization = Optional.of(new Organization());
//		List<Employment> employment = new ArrayList<>();
//		
//		mockPersonnel.setPersonal(personal.get());
//		mockPersonnel.setOrganization(organization.get());
//		mockPersonnel.setEmployment(employment);
//		when(this.personalRepository.findById(Mockito.anyLong())).thenReturn(personal);
//		when(this.organizationRepository.findById(Mockito.anyLong())).thenReturn(organization);
//		when(this.employmentRepository.findAll()).thenReturn(employment);
//		
//		personnel = userCreatorImpl.setUserPersonnel();
//		assertEquals(personnel.toString(), mockPersonnel.toString());
//	}
//}
