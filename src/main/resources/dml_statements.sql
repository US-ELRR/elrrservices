-- configuration table

INSERT INTO "configuration"
(configurationid, inserteddate, lastmodified, updatedby, configurationname, configurationvalue, recordstatus, frequency, starttime, contactname, email, orgname, phone, primarycontact, primaryemail, primaryorgname, primaryphone, secondarycontact, secondaryemail, secondaryorgname, secondaryphone)
VALUES(2, '2021-06-29 00:00:00.000', NULL, NULL, 'ADL Authoritative LRS', 'https://yet-lrs-v3.usalearning.net/xapi', 'A', '3 Weeks', '2:00 Saturday EST', 'Smith Smithson', 'SysSupport@USN.mil', 'Navy', '8003210212', 'Smith Smithson', 'SysSupport@USN.mil', 'Navy', '8003210212', NULL, NULL, NULL, NULL);
INSERT INTO "configuration"
(configurationid, inserteddate, lastmodified, updatedby, configurationname, configurationvalue, recordstatus, frequency, starttime, contactname, email, orgname, phone, primarycontact, primaryemail, primaryorgname, primaryphone, secondarycontact, secondaryemail, secondaryorgname, secondaryphone)
VALUES(3, '2021-06-29 00:00:00.000', NULL, NULL, 'Rustici LRS', 'https://rustici-dev.lrs.io/xapi', 'A', '2 Weeks', '0:00 Sunday EST', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "configuration"
(configurationid, inserteddate, lastmodified, updatedby, configurationname, configurationvalue, recordstatus, frequency, starttime, contactname, email, orgname, phone, primarycontact, primaryemail, primaryorgname, primaryphone, secondarycontact, secondaryemail, secondaryorgname, secondaryphone)
VALUES(1, '2021-06-29 00:00:00.000', NULL, NULL, 'Deloitte LRS', 'https://deloitte-prototype-noisy.lrs.io/xapi', 'A', '2 Weeks', '0:00 Sunday EST', 'John Johnson', 'SysAdmin@USAF.mil', 'USAF', '8003301212', 'John Johnson', 'SysAdmin@USAF.mil', 'USAF', '8003301212', 'David Lyod', 'david.lyod@gmail.com', 'USAF', '8002123456');

--imports table

INSERT INTO imports
(importid, importname, importstartdate, importenddate, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 'ADL Authoritative LRS', '2021-06-20', '2021-06-21', 'SUCCESS', NULL, NULL, NULL);
INSERT INTO imports
(importid, importname, importstartdate, importenddate, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 'Deloitte LRS', '2021-07-19', '2021-07-19', 'INPROCESS', NULL, NULL, '2021-07-19 16:33:00.018');

--competency table

delete from competency;

INSERT INTO competency
(competencyid, competencydefinitionidentifier, competencydefinitionidentifierurl, competencytaxonomyid, competencydefinitionvalidstartdate, competencydefinitionvalideenddate, competencydefinitionparentidentifier, competencydefinitionparenturl, competencydescriptionparentcode, competencydefinitioncode, competencydefinitiontype, competencydefinitiontypeurl, competencydefinitionstatement, recordstatus, updatedby, inserteddate, lastmodified, competencyframeworktitle, competencyframeworkversion, competencyframeworkidentifier, competencyframeworkdescription, competencyframeworksubject, competencyframeworkvalidstartdate, competencyframeworkvalidenddate)
VALUES(100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Skill and Roles: Business Skills and Acumen', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO competency
(competencyid, competencydefinitionidentifier, competencydefinitionidentifierurl, competencytaxonomyid, competencydefinitionvalidstartdate, competencydefinitionvalideenddate, competencydefinitionparentidentifier, competencydefinitionparenturl, competencydescriptionparentcode, competencydefinitioncode, competencydefinitiontype, competencydefinitiontypeurl, competencydefinitionstatement, recordstatus, updatedby, inserteddate, lastmodified, competencyframeworktitle, competencyframeworkversion, competencyframeworkidentifier, competencyframeworkdescription, competencyframeworksubject, competencyframeworkvalidstartdate, competencyframeworkvalidenddate)
VALUES(101, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Contract Principles: General Contracting Concepts', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO competency
(competencyid, competencydefinitionidentifier, competencydefinitionidentifierurl, competencytaxonomyid, competencydefinitionvalidstartdate, competencydefinitionvalideenddate, competencydefinitionparentidentifier, competencydefinitionparenturl, competencydescriptionparentcode, competencydefinitioncode, competencydefinitiontype, competencydefinitiontypeurl, competencydefinitionstatement, recordstatus, updatedby, inserteddate, lastmodified, competencyframeworktitle, competencyframeworkversion, competencyframeworkidentifier, competencyframeworkdescription, competencyframeworksubject, competencyframeworkvalidstartdate, competencyframeworkvalidenddate)
VALUES(102, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SERVICE3', NULL, NULL, NULL, NULL, NULL, NULL);

---

delete from role;


INSERT INTO role
(roleid, rolename, status, updatedby, inserteddate, lastmodified)
VALUES(1, 'TRAINING_MANAGER    ', 'Y         ', NULL, NULL, NULL);
INSERT INTO role
(roleid, rolename, status, updatedby, inserteddate, lastmodified)
VALUES(2, 'CAREER_MANAGER      ', 'Y         ', NULL, NULL, NULL);
INSERT INTO role
(roleid, rolename, status, updatedby, inserteddate, lastmodified)
VALUES(3, 'LEARNER             ', 'Y         ', NULL, NULL, NULL);

---

delete from rolerelations;

INSERT INTO rolerelations
(parentroleid, parentpersonid, childroleid, childpersonid, status, updatedby, inserteddate, lastmodified, id, id1)
VALUES(1, 106, 3, 101, 'Y         ', NULL, NULL, NULL, 2, 1);
INSERT INTO rolerelations
(parentroleid, parentpersonid, childroleid, childpersonid, status, updatedby, inserteddate, lastmodified, id, id1)
VALUES(1, 106, 3, 100, 'Y         ', NULL, NULL, NULL, 2, 2);
INSERT INTO rolerelations
(parentroleid, parentpersonid, childroleid, childpersonid, status, updatedby, inserteddate, lastmodified, id, id1)
VALUES(1, 106, 3, 102, 'Y         ', NULL, NULL, NULL, 2, 3);
INSERT INTO rolerelations
(parentroleid, parentpersonid, childroleid, childpersonid, status, updatedby, inserteddate, lastmodified, id, id1)
VALUES(1, 106, 3, 104, 'Y         ', NULL, NULL, NULL, 2, 6);
INSERT INTO rolerelations
(parentroleid, parentpersonid, childroleid, childpersonid, status, updatedby, inserteddate, lastmodified, id, id1)
VALUES(2, 105, 3, 104, 'Y         ', NULL, NULL, NULL, 2, 5);
INSERT INTO rolerelations
(parentroleid, parentpersonid, childroleid, childpersonid, status, updatedby, inserteddate, lastmodified, id, id1)
VALUES(2, 105, 3, 100, 'Y         ', NULL, NULL, NULL, NULL, 4);
