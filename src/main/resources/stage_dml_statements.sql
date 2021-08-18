-- configuration table

DELETE FROM STAGING.CONFIGURATION;

INSERT INTO STAGING.CONFIGURATION
(configurationid, configurationname, configurationvalue, frequency, starttime, 
 primarycontact, primaryemail, primaryorgname, primaryphone, 
secondarycontact, secondaryemail, secondaryorgname, secondaryphone, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 'Deloitte LRS', 'https://deloitte-prototype-noisy.lrs.io/xapi', '2 Weeks', '0:00 Sunday EST', 
 'John Johnson', 'SysAdmin@USAF.mil', 'USAF', '1-800-330-1212', 
'David Lyod', 'david.lyod@gmail.com', 'USAF', '1-800-212-3456', 
'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO STAGING.CONFIGURATION
(configurationid, configurationname, configurationvalue,  frequency, starttime, 
primarycontact, primaryemail, primaryorgname, primaryphone, 
secondarycontact, secondaryemail, secondaryorgname, secondaryphone, 
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 'ADL Authoritative LRS', 'https://yet-lrs-v3.usalearning.net/xapi',  '3 Weeks', '2:00 Saturday EST',
'Smith Smithson', 'SysSupport@USN.mil', 'Navy', '1-800-321-0212', 
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);

INSERT INTO STAGING.CONFIGURATION
(configurationid, configurationname, configurationvalue, frequency, starttime, 
primarycontact, primaryemail, primaryorgname, primaryphone, 
secondarycontact, secondaryemail, secondaryorgname, secondaryphone,
recordstatus, updatedby, inserteddate, lastmodified)
VALUES(3, 'Rustici LRS', 'https://rustici-dev.lrs.io/xapi', '2 Weeks', '0:00 Sunday EST', 
NULL, NULL, NULL, NULL, 
NULL, NULL, NULL, NULL, 
'ACTIVE', NULL, '2021-06-29', NULL);

--import table

DELETE FROM STAGING.IMPORT;

INSERT INTO STAGING.IMPORT
(importid, importname, importstartdate, importenddate, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(1, 'Deloitte LRS', '2021-07-19', '2021-07-19', 'SUCCESS', NULL, '2021-07-19', NULL);

INSERT INTO STAGING.IMPORT
(importid, importname, importstartdate, importenddate, recordstatus, updatedby, inserteddate, lastmodified)
VALUES(2, 'ADL Authoritative LRS', '2021-06-20', '2021-06-21', 'SUCCESS', NULL, '2021-07-21', NULL);

