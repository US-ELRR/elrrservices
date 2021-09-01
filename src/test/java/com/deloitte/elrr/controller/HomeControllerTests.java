package com.deloitte.elrr.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.elrr.ElrrApplication;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.entity.Personnel;
import com.deloitte.elrr.svc.LearnerCreatorSvc;

@SpringBootTest(classes = ElrrApplication.class)
@RunWith(SpringRunner.class)
class HomeControllerTests {

	@Mock
	LearnerCreatorSvc userCreatorSvc;
	@InjectMocks
	HomeController homeController;
	
	@Test
	void HomeControllerGetUsersTests() {
		String personId= "100";
		Learner learner = new Learner();
		Personnel personnel= new Personnel();
		List<Course> courses = new ArrayList<>();
		List<Competency> competencies = new ArrayList<>();
		Learner mockLearner = new Learner();
		mockLearner.setPersonnel(personnel);
		mockLearner.setCourses(courses);
		mockLearner.setCompetencies(competencies);
		when(userCreatorSvc.learnerCreator(personId)).thenReturn(mockLearner);
		learner = homeController.getLearner("Mohan","password");
		assert(learner.equals(mockLearner));
	}
}
