package com.deloitte.elrr.svc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.Personnel;
import com.deloitte.elrr.repository.CompetencyRepository;
import com.deloitte.elrr.repository.CourseRepository;
import com.deloitte.elrr.repository.EmploymentRepository;
import com.deloitte.elrr.repository.OrganizationRepository;
import com.deloitte.elrr.repository.PersonalRepository;


@Service
public class LearnerCreatorImpl implements LearnerCreatorSvc{
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CompetencyRepository competencyRepository;
	@Autowired
	private PersonalRepository personalRepository;
	@Autowired
	private EmploymentRepository employmentRepository;
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public Learner learnerCreator(String personId) {
		Learner learner = new Learner();
		List<Course> courses = this.courseRepository.findAll();
		List<Competency> competencies = this.competencyRepository.findAll();
		
		learner.setCourses(courses);
		learner.setCompetencies(competencies);
		learner.setPersonnel(setLearnerPersonnel());
		return learner;
	}
	
	public Personnel setLearnerPersonnel() {
		Personnel personnel = new Personnel();
		Optional<Person> person = Optional.of(new Person());
		Optional<Organization> organization = Optional.of(new Organization());

		person = this.personalRepository.findById(33L);
		System.out.println(person.get().toString());
		
		organization = Optional.of(this.organizationRepository.findAll().get(0));
		System.out.println(organization.get().toString());
		
		personnel.setPerson(person.get());
		personnel.setOrganization(organization.get());
		personnel.setEmployment(this.employmentRepository.findAll());
		
		return personnel;
	}
}
