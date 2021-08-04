CREATE SCHEMA ELRR;

Drop table IF EXISTS ELRR.CONFIGURATION;
 
CREATE TABLE ELRR.CONFIGURATION (
	configurationid int8 NOT NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	updatedby varchar(255) NULL,
	configurationname varchar(255) NOT NULL,
	configurationvalue varchar(255) NULL,
	recordstatus varchar(255) NULL,
	frequency varchar(255) NULL,
	starttime varchar(255) NULL,
	contactname varchar(255) NULL,
	email varchar(255) NULL,
	orgname varchar(255) NULL,
	phone varchar(255) NULL,
	primarycontact varchar(255) NULL,
	primaryemail varchar(255) NULL,
	primaryorgname varchar(255) NULL,
	primaryphone varchar(255) NULL,
	secondarycontact varchar(255) NULL,
	secondaryemail varchar(255) NULL,
	secondaryorgname varchar(255) NULL,
	secondaryphone varchar(255) NULL,
	CONSTRAINT configuration_pkey PRIMARY KEY (configurationid)
);


DROP TABLE IF EXISTS ELRR.COMPETENCY;

CREATE TABLE ELRR.COMPETENCY (
	competencyid int4 NOT NULL,
	competencydefinitionidentifier varchar(100) NULL,
	competencydefinitionidentifierurl text NULL,
	competencytaxonomyid varchar(100) NULL,
	competencydefinitionvalidstartdate date NULL,
	competencydefinitionvalideenddate date NULL,
	competencydefinitionparentidentifier varchar(100) NULL,
	competencydefinitionparenturl text NULL,
	competencydescriptionparentcode varchar(100) NULL,
	competencydefinitioncode varchar(100) NULL,
	competencydefinitiontype varchar(100) NULL,
	competencydefinitiontypeurl text NULL,
	competencydefinitionstatement text NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	competencyframeworktitle varchar(100) NOT NULL,
	competencyframeworkversion varchar(100) NULL,
	competencyframeworkidentifier varchar(100) NULL,
	competencyframeworkdescription text NULL,
	competencyframeworksubject varchar(100) NULL,
	competencyframeworkvalidstartdate date NULL,
	competencyframeworkvalidenddate date NULL,
	CONSTRAINT pk15 PRIMARY KEY (competencyid)
);


DROP TABLE IF EXISTS ELRR.CONTACTINFORMATION;

CREATE TABLE ELRR.CONTACTINFORMATION (
	contactinformationid int4 NOT NULL,
	personid int4 NOT NULL,
	contactinformation varchar(20) NOT NULL,
	telephonenumber varchar(20) NULL,
	isprimaryindicator bpchar(1) NULL,
	telephonetype varchar(20) NULL,
	electronicmailaddress varchar(320) NULL,
	electronicmailaddresstype varchar(20) NULL,
	emergencycontact varchar(20) NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	CONSTRAINT pk5 PRIMARY KEY (contactinformationid)
);



ALTER TABLE ELRR.CONTACTINFORMATION ADD CONSTRAINT refperson20 FOREIGN KEY (personid) REFERENCES person(personid);

DROP TABLE IF EXISTS ELRR.COURSE;
 
CREATE TABLE ELRR.COURSE (
	courseid int4 NOT NULL,
	coursetitle varchar(300) NOT NULL,
	coursesubjectmatter varchar(100) NULL,
	coursesubjectabbreviation varchar(20) NULL,
	courseidentifier varchar(50) NOT NULL,
	courselevel varchar(50) NULL,
	coursenumber varchar(50) NULL,
	courseinstructionmethod varchar(50) NULL,
	coursestartdate date NULL,
	courseenddate date NULL,
	courseenrollmentdate date NULL,
	courseacademicgrade varchar(50) NULL,
	courseprovidername varchar(100) NULL,
	departmentname varchar(100) NULL,
	coursegradescalecode varchar(50) NULL,
	coursemetadatarepository varchar(50) NULL,
	courselrsendpoint varchar(50) NULL,
	coursedescription text NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	CONSTRAINT pk10 PRIMARY KEY (courseid)
);

DROP TABLE IF EXISTS ELRR.ELRRAUDITLOG;

CREATE TABLE ELRR.ELRRAUDITLOG (
	auditlogid int8 NOT NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	updatedby varchar(255) NULL,
	payload jsonb NULL,
	syncid int8 NOT NULL,
	CONSTRAINT elrrauditlog_pkey PRIMARY KEY (auditlogid)
);

DROP TABLE IF EXISTS ELRR.EMPLOYMENT;

CREATE TABLE ELRR.EMPLOYMENT (
	employmentid int4 NOT NULL,
	employername varchar(100) NOT NULL,
	employerdepartment varchar(100) NULL,
	hiredate date NULL,
	employmentstartdate date NULL,
	employmentenddate date NULL,
	joblevel varchar(100) NULL,
	occupation varchar(100) NULL,
	employed bpchar(1) NULL,
	primarycarrercategory varchar(50) NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	secondcarrercategory varchar(255) NULL,
	CONSTRAINT pk8 PRIMARY KEY (employmentid)
);

DROP TABLE IF EXISTS ELRR.LEARNERPROFILE; 

CREATE TABLE ELRR.LEARNERPROFILE (
	learnerprofileid int4 NOT NULL,
	personid int4 NOT NULL,
	learneraddressid int4 NULL,
	contactinformationid int4 NULL,
	employmentid int4 NOT NULL,
	positionid int4 NULL,
	citizenshipid int4 NULL,
	studentid int4 NULL,
	courseid int4 NULL,
	courseaccreditationid int4 NULL,
	competencyid int4 NULL,
	credentialid int4 NULL,
	organizationid int4 NULL,
	organizationaddressid int4 NULL,
	accreditationid int4 NULL,
	activitystatus bpchar(10) NOT NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL
);

 
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refcitizenship36 FOREIGN KEY (citizenshipid) REFERENCES citizenship(citizenshipid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refcompetency41 FOREIGN KEY (competencyid) REFERENCES competency(competencyid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refcontactinformation25 FOREIGN KEY (contactinformationid) REFERENCES contactinformation(contactinformationid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refcourse39 FOREIGN KEY (courseid) REFERENCES course(courseid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refcourseaccreditation40 FOREIGN KEY (courseaccreditationid) REFERENCES courseaccreditation(courseaccreditationid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refcredential42 FOREIGN KEY (credentialid) REFERENCES credential(credentialid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refemployment25 FOREIGN KEY (employmentid) REFERENCES employment(employmentid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT reflearneraddress21 FOREIGN KEY (learneraddressid) REFERENCES learneraddress(learneraddressid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT reforganization43 FOREIGN KEY (organizationid) REFERENCES organization(organizationid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT reforganizationaddress44 FOREIGN KEY (organizationaddressid) REFERENCES organizationaddress(organizationaddressid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refposition26 FOREIGN KEY (positionid) REFERENCES position(positionid);
ALTER TABLE ELRR.LEARNERPROFILE ADD CONSTRAINT refstudent38 FOREIGN KEY (studentid) REFERENCES student(studentid);

DROP TABLE IF EXISTS ELRR.ORGANIZATION; 

CREATE TABLE ELRR.ORGANIZATION (
	organizationid int4 NOT NULL,
	organizationname varchar(100) NOT NULL,
	organizationidentifier varchar(100) NULL,
	organizationidentificationcode varchar(100) NULL,
	organizationidentificationsystem varchar(100) NULL,
	industrytypeidentifier varchar(100) NULL,
	organizationfein varchar(100) NULL,
	organizationdescription text NULL,
	parentorganization varchar(100) NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	CONSTRAINT pk9 PRIMARY KEY (organizationid)
);


DROP TABLE IF EXISTS ELRR.PERSON; 
 

CREATE TABLE ELRR.PERSON (
	personid int4 NOT NULL,
	name varchar(250) NOT NULL,
	firstname varchar(50) NOT NULL,
	middlename varchar(50) NULL,
	lastname varchar(50) NOT NULL,
	nameprefix varchar(50) NULL,
	titleaffixcode varchar(50) NULL,
	namesuffix varchar(50) NULL,
	qualificationaffixcode varchar(50) NULL,
	maidenname varchar(50) NULL,
	preferredname varchar(50) NULL,
	humanresourceidentifier text NULL,
	personnelidentificationsystem text NULL,
	birthdate date NULL,
	sex bpchar(1) NULL,
	primarylanguage varchar(50) NULL,
	militaryveteranindicator bpchar(1) NULL,
	recordstatus varchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate timestamp NULL,
	lastmodified timestamp NULL,
	namenuffix varchar(255) NULL,
	CONSTRAINT pk4 PRIMARY KEY (personid)
);

DROP TABLE IF EXISTS ELRR.ROLE; 

CREATE TABLE ELRR.ROLE (
	roleid int4 NOT NULL,
	rolename bpchar(20) NULL,
	status bpchar(10) NULL,
	updatedby varchar(20) NULL,
	inserteddate date NULL,
	lastmodified date NULL,
	CONSTRAINT pk16 PRIMARY KEY (roleid)
);

DROP TABLE IF EXISTS ELRR.ROLERELATIONS; 

CREATE TABLE ELRR.ROLERELATIONS (
	parentroleid int4 NOT NULL,
	parentpersonid int4 NOT NULL,
	childroleid int4 NOT NULL,
	childpersonid int4 NOT NULL,
	status bpchar(10) NOT NULL,
	updatedby varchar(20) NULL,
	inserteddate date NULL,
	lastmodified date NULL,
	id int4 NULL,
	id1 int4 NOT NULL DEFAULT 1
);

 