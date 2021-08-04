-- configuration table

DELETE FROM STAGE.CONFIGURATION;

INSERT INTO STAGE.CONFIGURATION
(configurationid, inserteddate, lastmodified, updatedby, configurationname, configurationvalue, recordstatus, frequency, starttime, contactname, email, orgname, phone, primarycontact, primaryemail, primaryorgname, primaryphone, secondarycontact, secondaryemail, secondaryorgname, secondaryphone)
VALUES(1, '2021-06-29 00:00:00.000', NULL, NULL, 'Deloitte LRS', 'https://deloitte-prototype-noisy.lrs.io/xapi', 'A', '2 Weeks', '0:00 Sunday EST', 'John Johnson', 'SysAdmin@USAF.mil', 'USAF', '8003301212', 'John Johnson', 'SysAdmin@USAF.mil', 'USAF', '8003301212', 'David Lyod', 'david.lyod@gmail.com', 'USAF', '8002123456');

INSERT INTO STAGE.CONFIGURATION
(configurationid, inserteddate, lastmodified, updatedby, configurationname, configurationvalue, recordstatus, frequency, starttime, contactname, email, orgname, phone, primarycontact, primaryemail, primaryorgname, primaryphone, secondarycontact, secondaryemail, secondaryorgname, secondaryphone)
VALUES(2, '2021-06-29 00:00:00.000', NULL, NULL, 'ADL Authoritative LRS', 'https://yet-lrs-v3.usalearning.net/xapi', 'A', '3 Weeks', '2:00 Saturday EST', 'Smith Smithson', 'SysSupport@USN.mil', 'Navy', '8003210212', 'Smith Smithson', 'SysSupport@USN.mil', 'Navy', '8003210212', NULL, NULL, NULL, NULL);

INSERT INTO STAGE.CONFIGURATION
(configurationid, inserteddate, lastmodified, updatedby, configurationname, configurationvalue, recordstatus, frequency, starttime, contactname, email, orgname, phone, primarycontact, primaryemail, primaryorgname, primaryphone, secondarycontact, secondaryemail, secondaryorgname, secondaryphone)
VALUES(3, '2021-06-29 00:00:00.000', NULL, NULL, 'Rustici LRS', 'https://rustici-dev.lrs.io/xapi', 'A', '2 Weeks', '0:00 Sunday EST', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--imports table

DELETE FROM STAGE.IMPORTS;

INSERT INTO STAGE.IMPORTS
(importid, importname, importstartdate, importenddate, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 'Deloitte LRS', '2021-07-19', '2021-07-19', 'INPROCESS', NULL, NULL, '2021-07-19 16:33:00.018');

INSERT INTO STAGE.IMPORTS
(importid, importname, importstartdate, importenddate, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 'ADL Authoritative LRS', '2021-06-20', '2021-06-21', 'SUCCESS', NULL, NULL, NULL);

