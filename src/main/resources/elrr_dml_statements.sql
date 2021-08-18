DELETE FROM ELRR.CONTACTINFORMATION;

INSERT INTO ELRR.CONTACTINFORMATION
(contactinformationid, personid, contactinformation, telephonenumber, isprimaryindicator, telephonetype, 
electronicmailaddress, electronicmailaddresstype, emergencycontact, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(100, 100, 'Email', '1-935-456-4578', 'Y', 'Private', 
'alex91@gmail.com', 'Personal', 'Email', 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.CONTACTINFORMATION
(contactinformationid, personid, contactinformation, telephonenumber, isprimaryindicator, telephonetype,
electronicmailaddress, electronicmailaddresstype, emergencycontact, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(101, 106, 'Email', '+1 403-443-5541', 'Y', 'Business', 
'glassliz@gmail.com', 'Business', 'Email', 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.CONTACTINFORMATION
(contactinformationid, personid, contactinformation, telephonenumber, isprimaryindicator, telephonetype, 
electronicmailaddress, electronicmailaddresstype, emergencycontact, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(102, 101, 'Email', '1-935-456-4578', 'Y', 'Private',
'Alexandrina@gmail.com', 'Personal', 'Email', 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.CONTACTINFORMATION
(contactinformationid, personid, contactinformation, telephonenumber, isprimaryindicator, telephonetype,
electronicmailaddress, electronicmailaddresstype, emergencycontact, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(103, 104, 'Email', '1-935-456-4578', 'Y', 'Private', 
'alice.smith@us.af.mil', 'Personal', 'Email', 'ACTIVE', NULL, '2021-06-29', NULL);


DELETE FROM ELRR.CONFIGURATION;


INSERT INTO ELRR.CONFIGURATION
(configurationid, configurationname, configurationvalue, frequency, starttime, 
 primarycontact, primaryemail, primaryorgname, primaryphone, 
secondarycontact, secondaryemail, secondaryorgname, secondaryphone, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 'Deloitte LRS', 'https://deloitte-prototype-noisy.lrs.io/xapi', '2 Weeks', '0:00 Sunday EST', 
 'John Johnson', 'SysAdmin@USAF.mil', 'USAF', '1-800-330-1212', 
'David Lyod', 'david.lyod@gmail.com', 'USAF', '1-800-212-3456', 
'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.CONFIGURATION
(configurationid, configurationname, configurationvalue,  frequency, starttime, 
primarycontact, primaryemail, primaryorgname, primaryphone, 
secondarycontact, secondaryemail, secondaryorgname, secondaryphone, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 'ADL Authoritative LRS', 'https://yet-lrs-v3.usalearning.net/xapi',  '3 Weeks', '2:00 Saturday EST',
'Smith Smithson', 'SysSupport@USN.mil', 'Navy', '1-800-321-0212', 
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.CONFIGURATION
(configurationid, configurationname, configurationvalue, frequency, starttime, 
primarycontact, primaryemail, primaryorgname, primaryphone, 
secondarycontact, secondaryemail, secondaryorgname, secondaryphone,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(3, 'Rustici LRS', 'https://rustici-dev.lrs.io/xapi', '2 Weeks', '0:00 Sunday EST', 
NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);

  
DELETE FROM ELRR.COMPETENCY;

INSERT INTO ELRR.COMPETENCY
(competencyid, competencydefinitionidentifier, competencydefinitionidentifierurl, competencytaxonomyid, competencydefinitionvalidstartdate,
competencydefinitionvalideenddate, competencydefinitionparentidentifier, competencydefinitionparenturl, competencydescriptionparentcode, 
competencydefinitioncode, competencydefinitiontype, competencydefinitiontypeurl, competencydefinitionstatement, 
competencyframeworktitle, competencyframeworkversion, competencyframeworkidentifier, 
competencyframeworkdescription, competencyframeworksubject, competencyframeworkvalidstartdate, competencyframeworkvalidenddate, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(100, NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL,
NULL, NULL, NULL, NULL,  
'Skill and Roles: Business Skills and Acumen', NULL, NULL,
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COMPETENCY
(competencyid, competencydefinitionidentifier, competencydefinitionidentifierurl, competencytaxonomyid, competencydefinitionvalidstartdate,
competencydefinitionvalideenddate, competencydefinitionparentidentifier, competencydefinitionparenturl, competencydescriptionparentcode, 
competencydefinitioncode, competencydefinitiontype, competencydefinitiontypeurl, competencydefinitionstatement,
competencyframeworktitle, competencyframeworkversion, competencyframeworkidentifier, 
competencyframeworkdescription, competencyframeworksubject, competencyframeworkvalidstartdate, competencyframeworkvalidenddate, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(101, NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL,
'Contract Principles: General Contracting Concepts', NULL, NULL, 
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);


INSERT INTO ELRR.COMPETENCY
(competencyid, competencydefinitionidentifier, competencydefinitionidentifierurl, competencytaxonomyid, competencydefinitionvalidstartdate,
competencydefinitionvalideenddate, competencydefinitionparentidentifier, competencydefinitionparenturl, competencydescriptionparentcode, 
competencydefinitioncode, competencydefinitiontype, competencydefinitiontypeurl, competencydefinitionstatement,
competencyframeworktitle, competencyframeworkversion, competencyframeworkidentifier, 
competencyframeworkdescription, competencyframeworksubject, competencyframeworkvalidstartdate, competencyframeworkvalidenddate, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(102, NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL, 
'SERVICE3', NULL, NULL, 
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);


DELETE FROM ELRR.COURSE; 

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber,
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname,
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(100, 'Fundamentals of Systems Acquisition Management', NULL, NULL, 'ACQ 101', NULL, '101',
'Web', '2020-12-21', '2021-01-15', '2020-12-01', NULL, 'DAU', 'Defense Acquisition University',
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname,
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(101, 'Mentoring the Acquisition Workforce', NULL, NULL, 'CLC 067', NULL, '67', 
'Web', '2021-01-15', '2021-01-25', '2021-01-03', NULL, 'DAU', 'Defense Acquisition University',
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname, 
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(102, 'Facilities Capital Cost of Money', NULL, NULL, 'CLC 103', NULL, '103', 
'Web', '2021-01-15', '2021-01-25', '2021-01-03', NULL, 'DAU', 'Defense Acquisition University', 
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);


INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber,
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname,
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(103, 'Analyzing Profit or Fee', NULL, NULL, 'CLC 104', NULL, '104', 
'Web', '2020-12-21', '2021-01-15', '2020-12-01', NULL, 'DAU', 'Defense Acquisition University', 
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname, 
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(104, 'Shaping Smart Business Arrangements', NULL, NULL, 'CON 100', NULL, '100', 
'Web', '2021-01-15', '2021-01-25', '2021-01-03', NULL, 'DAU', 'Defense Acquisition University', 
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname, 
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(105, 'Contracting Fundamentals', NULL, NULL, 'CON 091', NULL, '091', 
'Web', '2020-12-21', '2021-01-15', '2020-12-01', NULL, 'DAU', 'Defense Acquisition University',
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname, 
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(106, 'Core Concepts for Requirements Management', NULL, NULL, 'RQM 110', NULL, '110', 
'Web', '2021-03-03', '2021-03-20', '2021-02-27', NULL, 'DAU', 'Defense Acquisition University',
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname,
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(107, 'Advanced Concepts and Skills for Requirements Management', NULL, NULL, 'RQM 310', NULL, '310', 
'Web', '2021-03-03', '2021-03-20', '2021-02-27', NULL, 'DAU', 'Defense Acquisition University',
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname, 
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(108, 'Requirements Executive Overview Workshop', NULL, NULL, 'RQM 403', NULL, '403', 
'Web', '2021-03-03', '2021-03-20', '2021-02-27', NULL, 'DAU', 'Defense Acquisition University', 
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname, 
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(109, 'Department of Defense (DoD) Cyber Awareness Challenge 2021 (1 hr) ', NULL, NULL, 'DOD-US1364-21', NULL, '21', 
'Web', '2021-03-03', '2021-03-20', '2021-02-27', NULL, 'JKO', 'Joint Knowledge Online', 
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.COURSE
(courseid, coursetitle, coursesubjectmatter, coursesubjectabbreviation, courseidentifier, courselevel, coursenumber, 
courseinstructionmethod, coursestartdate, courseenddate, courseenrollmentdate, courseacademicgrade, courseprovidername, departmentname,
coursegradescalecode, coursemetadatarepository, courselrsendpoint, coursedescription, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(110, 'HH-60 AIRCRAFT MAINTENANCE OFFICER/SUPERVISOR FAMILIARIZATION', NULL, NULL, 'J4OMP21A3  A30A', NULL, '30',
'Web', '2021-03-03', '2021-03-20', '2021-02-27', NULL, 'AETC', 'Air Education and Training Command',
NULL, NULL, NULL, NULL, 'ACTIVE', NULL, '2021-06-29', NULL);


DELETE FROM ELRR.EMPLOYMENT;

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(100, 'NAVY', '', '2012-06-30', '2012-07-07', NULL, 'Petty Officer Third Class', 'Mass Communications Specialist', 'Y', 
NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(101, 'NAVY', '', '2012-06-30', '2012-07-07', NULL, 'Petty Officer First Class', 'Navy Counselor', 'Y',
NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(102, 'NAVY', '', '2012-06-30', '2012-07-07', NULL, 'Petty Officer Second Class', 'Legalmen', 'Y',
NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(103, 'NAVY', '', '2018-07-07', '2018-07-27', NULL, 'Senior Chief Petty Officer', 'Personnel Specialist', 'Y', 
NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(104, 'USAF', 'DoD Air Force Acquisitions', '2012-06-30', '2012-07-05', NULL, '', 'Manager of Team Acquisitions', 'Y',  
NULL,'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(105, 'USAF', 'DoD Air Force Acquisitions', '2012-06-30', '2010-07-07', NULL, '', '', 'Y',
NULL, 'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO ELRR.EMPLOYMENT
(employmentid, employername, employerdepartment, hiredate, employmentstartdate, employmentenddate, joblevel, occupation, employed, 
primarycareercategory, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(106, 'USAF', 'DoD Air Force Acquisitions', '2019-05-07', '2019-05-25', NULL, '', '', 'Y', 
NULL, 'ACTIVE', NULL, '2021-06-29', NULL);


DELETE FROM ELRR.LEARNERPROFILE; 

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 100, NULL, NULL, 100, NULL, NULL, NULL, 100, 
NULL, 100, NULL, 100, NULL, 1, 'completed', 
'ACTIVE', NULL, '2021-04-29', NULL);


INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 100, NULL, NULL, 100, NULL, NULL, NULL, 101,
NULL, 101, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-04-29', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(3, 100, NULL, NULL, 100, NULL, NULL, NULL, 102, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-04-29', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(4, 101, NULL, 101, 100, NULL, NULL, NULL, 102,
NULL, 100, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-04-29', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(5, 106, NULL, NULL, 100, NULL, NULL, NULL, 100, 
NULL, 100, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-04-29', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(6, 106, NULL, NULL, 100, NULL, NULL, NULL, 101, 
NULL, 101, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-04-29', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(7, 106, NULL, NULL, 100, NULL, NULL, NULL, 102,
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-04-29', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(8, 106, NULL, NULL, 100, NULL, NULL, NULL, 108,
NULL, 100, NULL, NULL, NULL, NULL, 'Completed', 
'ACTIVE', NULL, '2021-07-15', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(9, 105, NULL, NULL, 100, NULL, NULL, NULL, 100,
NULL, 100, NULL, 100, NULL, 1, 'Completed ',
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(10, 105, NULL, NULL, 100, NULL, NULL, NULL, 101, 
NULL, 101, NULL, 100, NULL, 1, 'Completed',
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(11, 104, NULL, 104, 100, NULL, NULL, NULL, 100, 
NULL, 100, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28 00:00:00.000', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(12, 104, NULL, 104, 100, NULL, NULL, NULL, 101,
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28 00:00:00.000', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(13, 104, NULL, 104, 100, NULL, NULL, NULL, 104, 
NULL, 101, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28 00:00:00.000', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(14, 104, NULL, 104, 100, NULL, NULL, NULL, 109, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);



INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(15, 106, NULL, NULL, 100, NULL, NULL, NULL, 109, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(16, 105, NULL, NULL, 100, NULL, NULL, NULL, 109, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);


INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(17, 106, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed',
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(18, 107, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed',
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(19, 108, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed',
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(20, 109, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(21, 110, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(22, 111, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid, 
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(23, 112, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed ', 
'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.LEARNERPROFILE
(learnerprofileid, personid, learneraddressid, contactinformationid, employmentid, positionid, citizenshipid, studentid, courseid,
courseaccreditationid, competencyid, credentialid, organizationid, organizationaddressid, accreditationid, activitystatus, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(24, 113, NULL, NULL, 100, NULL, NULL, NULL, 110, 
NULL, NULL, NULL, 100, NULL, 1, 'Completed', 
'ACTIVE', NULL, '2021-06-28', NULL);


DELETE FROM ELRR.ORGANIZATION;


INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem,
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(100, 'NAVY', 'D0DAF', 'G0V4', NULL, 
NULL, '1234573', 'NAVY', 'NAVY', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem,
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(101, 'AETC1', 'D0DAF', 'G0V4', NULL,
NULL, '1234572', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem, 
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(102, 'AETC2', 'D0DAF', 'G0V4', NULL,
NULL, '1234571', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem, 
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(103, 'AETC3', 'D0DAF', 'G0V4', NULL,
NULL, '1234570', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem, 
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(104, 'AETC4', 'D0DAF', 'G0V4', NULL,
NULL, '1234569', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem,
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(105, 'AETC5', 'D0DAF', 'G0V4', NULL, 
NULL, '1234568', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem, 
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(106, 'AETC', 'D0DAF', 'G0V4', NULL,
NULL, '1234567', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem,
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(107, 'AETC', 'D0DAF', 'G0V4', NULL, 
NULL, '1234565', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ORGANIZATION
(organizationid, organizationname, organizationidentifier, organizationidentificationcode, organizationidentificationsystem, 
industrytypeidentifier, organizationfein, organizationdescription, parentorganization, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(108, 'AETC', 'D0DAF', 'G0V4', NULL,
NULL, '1234563', 'DoD AIR FORCE', 'AIR FORCE', 'ACTIVE', NULL, '2021-06-28', NULL);


DELETE FROM ELRR.PERSON;


INSERT INTO ELRR.PERSON
(personid, name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(100, 'Alexandrina Annabelle Arredondo-Arteaga', 'Alexandrina', 'Annabelle', 'Arredondo-Arteaga', 'Ms.', NULL, NULL, 
NULL, 'Atkinson-Abbas', NULL, '3599900000', NULL, '2000-12-31', 
'F', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(101, 'Brinleigh Belarmino Blanchard', 'Brinleigh', 'Belarmino', 'Blanchard', 'Adm.', NULL, NULL, 
NULL, 'Bragg', NULL, '3599900010', NULL, '1999-10-03', 
'M', 'English', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(102, 'Christopher Cooper Cunningham', 'Christopher', 'Cooper', 'Cunningham', 'Mr.', NULL, NULL, 
NULL, 'Champion', NULL, '3518193230', NULL, '1995-01-02', 
'M', 'English', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(103, 'Dominick Delarosa', 'Dominick', '', 'Delarosa', '', NULL, NULL, 
NULL, 'Davenport', NULL, NULL, NULL, '1994-02-05', 
'M', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);


INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate,
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(104, 'Alice Beth Smith', 'Alice', 'Beth', 'Smith', 'Ms.', NULL, NULL,
NULL, 'Smith', NULL, NULL, NULL, '1985-07-04',
'F', 'English', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(105, 'Bill Christian Phillips', 'Bill', 'Christian', 'Phillips', 'Mr.', NULL, NULL, 
NULL, 'Phillips', NULL, NULL, NULL, '1976-03-19',
'M', 'English', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(106, 'Liz May Glass', 'Liz', 'May', 'Glass', 'Mrs.', NULL, NULL,
NULL, 'Richards', NULL, NULL, NULL, '1966-05-18', 
'F', 'English', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(107, 'Michael A Christopher', 'Michael', 'A', 'Christopher', 'Ms.', NULL, NULL,
NULL, 'Michael-Christopher', NULL, '3599900000', NULL, '2000-12-31', 
'F', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(108, 'Jessica B Matthew', 'Jessica', 'B', 'Matthew', 'Ms.', NULL, NULL, 
NULL, 'Jessica-Matthew', NULL, '3599900000', NULL, '2000-12-30', 
'F', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(109, 'Ashley C Jennifer', 'Ashley', 'C', 'Jennifer', 'Ms.', NULL, NULL, 
NULL, 'Ashley-Jennifer', NULL, NULL, NULL, '2000-12-18', 
'F', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(110, 'Joshua D Amanda', 'Joshua', 'D', 'Amanda', 'Mr.', NULL, NULL,
NULL, 'Joshua-Amanda', NULL, 'NULL, NULL, '2000-11-25', 
'M', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(111, 'Daniel F David', 'Daniel', 'F', 'David', 'Mr.', NULL, NULL, 
NULL, 'Daniel-David', NULL, NULL, NULL, '2000-12-31', 
'M', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(112, 'James G Robert', 'James', 'G', 'Robert', 'Mr.', NULL, NULL, 
NULL, 'James-Robert', NULL, NULL, NULL, '2000-08-21', 
'M', 'Spanish',  NULL, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.PERSON
(personid,name, firstname, middlename, lastname, nameprefix, titleaffixcode, namesuffix, 
qualificationaffixcode, maidenname, preferredname, humanresourceidentifier, personnelidentificationsystem, birthdate, 
sex, primarylanguage, militaryveteranindicator, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(113, 'John H Joseph', 'John', 'A', 'Joseph', 'Mr.', NULL, NULL, 
NULL, 'John-Joseph', NULL, '3599900000', NULL, '2000-12-31', 
'F', 'Spanish', NULL, 'ACTIVE', NULL, '2021-06-28', NULL);



DELETE FROM ELRR.ROLE;

--status to recrod status

INSERT INTO ELRR.ROLE
(roleid, rolename, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 'TRAINING_MANAGER', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ROLE
(roleid, rolename, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 'CAREER_MANAGER', 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ROLE
(roleid, rolename, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(3, 'LEARNER', 'ACTIVE', NULL, NULL, '2021-06-28');


DELETE FROM ELRR.ROLERELATIONS;

--status to recrod status

INSERT INTO ELRR.ROLERELATIONS
(rolerelationsid, parentroleid, parentpersonid, childroleid, childpersonid, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 1, 106, 3, 101, 'ACTIVE', NULL, '2021-06-28', NULL );

INSERT INTO ELRR.ROLERELATIONS
(rolerelationsid, parentroleid, parentpersonid, childroleid, childpersonid, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 1, 106, 3, 100, 'ACTIVE', NULL, '2021-06-28', NULL );

INSERT INTO ELRR.ROLERELATIONS
(rolerelationsid, parentroleid, parentpersonid, childroleid, childpersonid, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(3, 1, 106, 3, 102, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ROLERELATIONS
(rolerelationsid, parentroleid, parentpersonid, childroleid, childpersonid, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(4, 1, 106, 3, 104, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ROLERELATIONS
(rolerelationsid, parentroleid, parentpersonid, childroleid, childpersonid, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(5, 2, 105, 3, 104, 'ACTIVE', NULL, '2021-06-28', NULL);

INSERT INTO ELRR.ROLERELATIONS
(rolerelationsid, parentroleid, parentpersonid, childroleid, childpersonid, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(6, 2, 105, 3, 100, 'ACTIVE', NULL, '2021-06-28', NULL);
