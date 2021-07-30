package com.deloitte.elrr.svc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.tools.DocumentationTool;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.elrr.controller.HomeController;
import com.deloitte.elrr.dto.AccreditationDto;
import com.deloitte.elrr.dto.CompetencyDto;
import com.deloitte.elrr.dto.ContactInformationDto;
import com.deloitte.elrr.dto.CourseDto;
import com.deloitte.elrr.dto.EmploymentDto;
import com.deloitte.elrr.dto.LearnerDTO;
import com.deloitte.elrr.dto.LearnerData;
import com.deloitte.elrr.dto.LearnerHeader;
import com.deloitte.elrr.dto.LearnerProfileDto;
import com.deloitte.elrr.dto.LearnerSnapshotDTO;
import com.deloitte.elrr.dto.LearnersDTO;
import com.deloitte.elrr.dto.OrganizationDto;
import com.deloitte.elrr.dto.PersonDto;
import com.deloitte.elrr.entity.Competency;
import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.entity.Course;
import com.deloitte.elrr.entity.Employment;
import com.deloitte.elrr.entity.Learner;
import com.deloitte.elrr.entity.LearnerProfile;
import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.entity.Person;
import com.deloitte.elrr.entity.Personnel;
import com.deloitte.elrr.entity.Role;
import com.deloitte.elrr.entity.RoleRelations;
import com.deloitte.elrr.repository.CompetencyRepository;
import com.deloitte.elrr.repository.ContactInformationRepository;
import com.deloitte.elrr.repository.CourseRepository;
import com.deloitte.elrr.repository.EmploymentRepository;
import com.deloitte.elrr.repository.LearnerProfileRepository;
import com.deloitte.elrr.repository.OrganizationRepository;
import com.deloitte.elrr.repository.PersonalRepository;
import com.deloitte.elrr.repository.RoleRelationsRepository;
import com.deloitte.elrr.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LearnerCreatorImpl implements LearnerCreatorSvc {

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
	@Autowired
	private LearnerProfileRepository learnerProfileRepository;
	@Autowired
	private ContactInformationRepository contactInformationRepository;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoleRelationsRepository roleRelationsRepository;

	@Autowired
	private RoleRepository roleRepository;

	private int maxSize =5;
	@Override
	public LearnerDTO learnerCreator(String personId) {

		LearnerDTO learnerDTO = new LearnerDTO();

		Long id = Long.valueOf(personId);

		List<RoleRelations> rolesRelations = roleRelationsRepository.findAll();
		List<RoleRelations> rolesRelationsList = rolesRelations.stream().filter(e -> e.getParentPersonid() == id)
				.collect(Collectors.toList());
		log.info(" rolerelations subset size" + rolesRelationsList.size());

		Learner learner = getLearner(personId);
		learnerDTO.setLearner(learner);
		learnerDTO.setRole(getRole(rolesRelationsList));
		List<Learner> assignedList = new ArrayList<>();
		for (RoleRelations roleRelations : rolesRelationsList) {
			String id1 = new Long(roleRelations.getChildPersonid()).toString();
			Learner learner1 = getLearner(id1);
			assignedList.add(learner1);
		}
		learnerDTO.setAssigned(assignedList);
		return learnerDTO;
	}

	private String getRole(List<RoleRelations> rolesRelationsList) {
		String roleName = "LEARNER";
		if (rolesRelationsList.size() > 0) {
			Optional<Role> role = roleRepository.findById(rolesRelationsList.get(0).getParentRoleid());
			roleName = role.get().getRoleName();
		}

		return roleName.trim();
	}

	private Learner getLearner(String personId) {
		return getLearner(personId, true, true);
	}
	private Learner getLearner(String personId, boolean coursesNeeded, boolean competenciesNeeded) {
		Learner learner = new Learner();
		List<LearnerProfile> profiles = getLearnerProfiles(personId);
		log.info("learnerProfiles " + profiles.size());
		if (coursesNeeded) {
			learner.setCourses(getCourses(profiles));
			log.info("courses " + learner.getCourses().size());
		}
		if (competenciesNeeded) {
			learner.setCompetencies(getCompetencies(profiles));
			log.info("competencies " + learner.getCompetencies().size());
		}
		learner.setPersonnel(getLearnerPersonnel(personId, profiles));
		return learner;
	}

	private List<CompetencyDto> getCompetencies(List<LearnerProfile> profiles) {

		List<CompetencyDto> list = new ArrayList<CompetencyDto>();
		for (LearnerProfile profile : profiles) {
			if (profile.getCompetencyid() != null) {
				Optional<Competency> competency = competencyRepository.findById(profile.getCompetencyid());
				CompetencyDto dto = mapper.map(competency.get(), CompetencyDto.class);
				list.add(dto);
			}
		}
		return list;
	}

	private List<CourseDto> getCourses(List<LearnerProfile> profiles) {

		List<CourseDto> list = new ArrayList<CourseDto>();
		for (LearnerProfile profile : profiles) {
			Optional<Course> course = courseRepository.findById(profile.getCourseid());
			CourseDto courseDto = mapper.map(course.get(), CourseDto.class);
			list.add(courseDto);
		}
		return list;
	}

	private List<LearnerProfile> getLearnerProfiles(String id) {
		Long personId = Long.valueOf(id);
		// TODO instead of doing findAll, query directly on personId
		List<LearnerProfile> list = learnerProfileRepository.findAll();
		List<LearnerProfile> profileList = list.stream().filter(e -> e.getPersonid() == personId)
				.collect(Collectors.toList());
		return profileList;

	}

	private Personnel getLearnerPersonnel(String personId, List<LearnerProfile> profiles) {

		Personnel personnel = new Personnel();
		OrganizationDto organizationDto = new OrganizationDto();
		// PersonDto personDto = new PersonDto();
		Optional<Person> person = this.personalRepository.findById(Long.valueOf(personId));
		if (profiles != null && profiles.size() > 0) {
			Optional<Organization> organization1 = this.organizationRepository
					.findById(profiles.get(0).getOrganizationid());
			organizationDto = mapper.map(organization1.get(), OrganizationDto.class);
		}
		PersonDto personDto = mapper.map(person.get(), PersonDto.class);

		// PersonDto personDto = mapper.map(person.get(), PersonDto.class);
		personnel.setPerson(personDto);
		personnel.setOrganization(organizationDto);
		personnel.setContactInformation(getContactInformation(personId));
		personnel.setEmployment(getEmployeeList(profiles));
		return personnel;
	}

	private ContactInformationDto getContactInformation(String id) {
		log.info("inside getContact " + id);
		Long personId = Long.valueOf(id);
		ContactInformation contactInformation = new ContactInformation();
		ContactInformationDto contactInformationDto = new ContactInformationDto();
		// TODO instead of doing findAll, query directly on personId
		List<ContactInformation> list = contactInformationRepository.findAll();
		log.info("contact list " + list);
		if (list != null && list.size() > 0) {
			List<ContactInformation> contactList = list.stream().filter(e -> e.getPersonid() == personId)
					.collect(Collectors.toList());
			log.info("contactList is " + contactList.size());
			if (contactList.size() > 0) {
				contactInformation = contactList.get(0);
			}
			log.info("contactInformation " + contactInformation.getContactinformationid());
			contactInformationDto = mapper.map(contactInformation, ContactInformationDto.class);
			log.info("contactInformationDto  " + contactInformationDto.getContactinformation());
		}
		return contactInformationDto;

	}

	private List<EmploymentDto> getEmployeeList(List<LearnerProfile> profiles) {
		List<EmploymentDto> list = new ArrayList<EmploymentDto>();
		if (profiles != null && profiles.size() > 0) {
			for (LearnerProfile profile : profiles) {
				Optional<Employment> employment1 = this.employmentRepository.findById(profile.getEmploymentid());
				EmploymentDto employmentDto = mapper.map(employment1.get(), EmploymentDto.class);
				list.add(employmentDto);
			}
		}
		return list;
	}

	/*
	 * This method invoked by controller to get Learners based on the date criteria,
	 * orgName, responseEntities and startRecord
	 */
	@Override
	public LearnersDTO getLearners(Date startDate, Date endDate, String orgName, String[] responseEntities,
			int startRecord) {

		LearnersDTO dto = new LearnersDTO();

		boolean coursesNeeded = false;
		boolean competenciesNeeded = false;
		
		if (responseEntities != null) {
			for (String str: responseEntities) {
				if (str.contains("COURSE")) {
					coursesNeeded = true;
				}
				if (str.contains("COMPETENCY")) {
					competenciesNeeded = true;
				}
			}
		} else {
			coursesNeeded = true;
			competenciesNeeded=true;
		}
		
		List<LearnerProfile> learnerProfiles = findLearnerProfiles(startDate, endDate, orgName);
		
		List<LearnerProfile> filtered = filterByStartAndEnd(learnerProfiles, startRecord);
		
		List<Learner> learnerList = new ArrayList<>();
		
		
		for (LearnerProfile profile : filtered) {

			Learner learner = getLearner(profile.getPersonid().toString(), coursesNeeded, competenciesNeeded);
			learnerList.add(learner);
		}

		LearnerHeader header = new LearnerHeader();
		header.setFromDate(startDate);
		header.setToDate(endDate);
		header.setOrgName(orgName);
		header.setStartRecord(startRecord);
		header.setEndRecord(filtered.size()+startRecord-1);
		header.setTotalCount(learnerProfiles.size());
		dto.setHeader(header);
		LearnerData data = new LearnerData();

		data.setLearner(learnerList);
		dto.setData(data);
		return dto;

	}

	private List<LearnerProfile> findLearnerProfiles(Date startDate, Date endDate, String orgName) {
		
		//TODO these two will be one method where repository will provide based on where condition
		List<LearnerProfile> learnerProfiles = learnerProfileRepository.findAll();
		List<LearnerProfile> list = filterProfiles(learnerProfiles, startDate, endDate, orgName);
		log.info("filtered List  " + list.size());

		return list;
	}

	/*
	 * Will retrieve all learner profiles based on the date range and orgName
	 * TODO some of this code will be part of the where condition
	 */
	private List<LearnerProfile> filterProfiles(List<LearnerProfile> learnerProfiles, Date startDate, Date endDate,
			String orgName) {
		List<LearnerProfile> list = new ArrayList<LearnerProfile>();
		long orgId = getOrgIdByName(orgName);
		List<Long> persons = new ArrayList<>();
		for (LearnerProfile profile : learnerProfiles) {
			boolean isBetweenDates = isBetweenDates(profile, startDate, endDate);
			if (isBetweenDates && isBelongsOrg(profile, orgId)) {
				//remove duplicates by personId
				if (!persons.contains(profile.getPersonid())) {
					list.add(profile);
					persons.add(profile.getPersonid());
				} 
			}
		}
		
		return list;
	}

	/*
	 * This method will filter records by startRecord and maxSize
	 */

	private List<LearnerProfile> filterByStartAndEnd(List<LearnerProfile> list, int startRecord) {

 		int end = maxSize;
		if (startRecord > 1) {
			startRecord = startRecord -1;
			if (list.size() - startRecord > maxSize) {
				end = startRecord + maxSize-1; 
			} else {
				end = list.size()-1;
			}
		}
		list = list.subList(startRecord,end+1);
		return list;

	}

	/*
	 * This method will get orgId by orgName
	 */

	private long getOrgIdByName(String orgName) {
		List<Organization> list = organizationRepository.findAll();
		for (Organization org : list) {
			if (org.getOrganizationname().equalsIgnoreCase(orgName)) {
				return org.getOrganizationid();
			}
		}
		return 0;
	}

	/*
	 * TODO this will be part of the repository where condition
	 * This method will filter records by dates between
	 */

	private boolean isBetweenDates(LearnerProfile profile, Date startDate, Date endDate) {
 		if (endDate != null) {
			if (profile.getInserteddate().after(startDate) && profile.getInserteddate().before(endDate)) {
				return true;
			}
		} else {
			if (profile.getInserteddate().after(startDate)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * TODO this will be part of the repository where condition
	 * This method will filter records by organization
	 */
	private boolean isBelongsOrg(LearnerProfile profile, long orgId) {

		if (profile.getOrganizationid() == orgId)
			return true;
		return false;
	}

	@Override
	public LearnerSnapshotDTO getSnapshot() {
		// TODO Auto-generated method stub
		
		learnerProfileRepository.count();
		LearnerSnapshotDTO snapshot = new LearnerSnapshotDTO();
		snapshot.setTotalLearnerProfiles(learnerProfileRepository.count());
		snapshot.setTotalLearners(personalRepository.count());
		snapshot.setTotalCourses(courseRepository.count());
		snapshot.setTotalCompetencies(competencyRepository.count());
		return snapshot;
	}
}
