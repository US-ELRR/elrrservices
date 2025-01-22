Services Outline

## Person

GET /person
- list of all people

GET /person/[id]
- single person including address rels but no others

GET /person/[id]/learningrecord
- list learner learningrecords

GET /person/[id]/phones

GET /person/[id]/emails

GET /person/[id]/employmentrecord

GET /person/[id]/militaryrecord

POST /person
- Save new person. Included addresses might be IDs, or full address DTOs without IDs (which will save new location objects)

PUT /person/[id]
- update and return person

#### Person-Org

GET /person/[id]/organization
- All orgs

GET /person/[id]/organization/[id]
- returns rel if exists

POST /person/[id]/organization/[id]
- Body only contains relationship_type
- Establishes rel, returns rel

PUT /person/[id]/organization/[id]
- Updates rel_type
- Returns rel

DELETE /person/[id]/organization/[id]
- Removes Rel, return success

#### Person-Comp

The calls below are the same for /competency and /credential

GET /person/[id]/competency
- list learner competencies

GET /person/[id]/competency/[id]
- returns rel if exists

POST /person/[id]/competency/[id] (& credential)
- may contain hasRecord
- inserts comp

PUT /person/[id]/competency/[id]
- Updates hasRecord
- Returns rel

DELETE /person/[id]/competency/[id]
- Removes Rel, return success

## Learning Record

GET /learningrecord
- All learning records
- Might have query params

GET /learningrecord/[id]
- Get All

POST /learningrecord
- Body must contain existing person and learning resource with valid IDs
- Creates learning record

PUT /learningrecord/[id]
- update learning record by id. Cannot change learner or resource

DELETE /learningrecord/[id]
- delete learning record by id

## Employment Record

GET /employmentrecord
- All employment records
- Might have query params

GET /employmentrecord/[id]
- Get All

POST /employmentrecord
- Body must contain existing person and org resource with valid IDs
- Creates employment record

PUT /employmentrecord/[id]
- update employment record by id. Cannot change learner or employer

DELETE /employmentrecord/[id]
- delete employment record by id

## Military Record

GET /militaryrecord
- All military records
- Might have query params

GET /militaryrecord/[id]
- Get All

POST /militaryrecord
- Body must contain existing person and org resource with valid IDs
- Creates military record

PUT /militaryrecord/[id]
- update military record by id. Cannot change learner or employer

DELETE /militaryrecord/[id]
- delete military record by id

## Organization

GET /organization

GET /organization/[id]

GET /organization/[id]/person

GET /organization/[id]/facility

POST /organization

PUT /organization/[id]

DELETE /organization/[id]

## Comp / Creds

GET /competency (& /credential)

GET /competency/[id]    

GET /competency/[id]/people

POST /competency

PUT /competency/[id]

DELETE /competency/[id]

## Facility

GET /facility

GET /facility/[id]

POST /facility

PUT /facility/[id]

DELETE /facility/[id]
