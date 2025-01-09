-- Service Database Schema for P2997 Alignment

DO $$ BEGIN
    CREATE TYPE military_status AS ENUM (
        'ACTIVE', 'RESERVE', 'SEPARATED', 'RETIRED');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

DO $$ BEGIN
    CREATE TYPE learning_status AS ENUM (
        'ATTEMPTED', 'COMPLETED', 'PASSED', 'FAILED');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

DO $$ BEGIN
    CREATE TYPE comp_cred_type AS ENUM (
        'COMPETENCY', 'CREDENTIAL');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS organization (
    id                          UUID PRIMARY KEY,
    name                        VARCHAR(255) NOT NULL,
    description                 TEXT,
    profit_type                 VARCHAR(255), --enum
    department                  VARCHAR(255), --check
    industry_code               VARCHAR(255), --enum?
    industry_category           VARCHAR(255), --enum / validated?
    vertical_specialization     VARCHAR(255),
    organization_identifier     VARCHAR(255),
    organization_duns           VARCHAR(255),
    organization_fein           VARCHAR(255),
    school_opeid                VARCHAR(255),
    ipeds_type                  VARCHAR(255),
    organization_isic           VARCHAR(255),
    organization_image          VARCHAR(255),
    organization_website        VARCHAR(255),
    --OrganizationType isnt a field, its a rel per person
    institution_level           VARCHAR(255), -- controlled vocab
    organization_accredits      VARCHAR(255),
    institution_revocation_list VARCHAR(255),
    has_verification_service    BOOLEAN,
    institution_verification    TEXT, -- instructions?
    organizational_resource     VARCHAR(255),
    -- OrganizationOwns is implied in other rels
    -- OrganizationRecognizes complex to implement
    -- OrganizationRegulates
    quality_assurance_type      VARCHAR(255), -- controlled vocab
    -- identification_system open question
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS location (
    id                          UUID PRIMARY KEY,
    street_number_and_name      VARCHAR(255),
    apartment_room_suite_number VARCHAR(255),
    city                        VARCHAR(255),
    state_abbreviation          VARCHAR(255),
    postal_code                 VARCHAR(255),
    county                      VARCHAR(255),
    country_code                VARCHAR(255),
    latitude                    VARCHAR(255),
    longitude                   VARCHAR(255),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS person (
    id                          UUID PRIMARY KEY,
    name                        VARCHAR(255),
    first_name                  VARCHAR(255),
    middle_name                 VARCHAR(255),
    last_name                   VARCHAR(255),
    name_prefix                 VARCHAR(255),
    title_affix_code            VARCHAR(255),
    name_suffix                 VARCHAR(255),
    qualification_affix_code    VARCHAR(255),
    maiden_name                 VARCHAR(255),
    mailing_address_id          UUID REFERENCES location (id),
    physical_address_id         UUID REFERENCES location (id),
    shipping_address_id         UUID REFERENCES location (id),
    billing_address_id          UUID REFERENCES location (id),
    on_campus_address_id        UUID REFERENCES location (id),
    off_campus_address_id       UUID REFERENCES location (id),
    temporary_address_id        UUID REFERENCES location (id),
    permanent_student_address_id    UUID REFERENCES location (id),
    employment_address_id       UUID REFERENCES location (id),
    time_of_admission_address_id    UUID REFERENCES location (id),
    father_address_id           UUID REFERENCES location (id),
    mother_address_id           UUID REFERENCES location (id),
    guardian_address_id         UUID REFERENCES location (id),
    birthdate                   DATE,
    birthplace                  UUID REFERENCES location (id),
    citizenship                 VARCHAR(255),
    height                      numeric(5, 2),
    height_unit                 VARCHAR(255), --check on this
    weight                      numeric(5, 2),
    weight_unit                 VARCHAR(255), --check on this
    interpupillary_distance     bigint,
    handedness                  VARCHAR(255), --check on this
    primary_language            VARCHAR(255), --check on this
    current_security_clearance  VARCHAR(255), --check on this
    highest_security_clearance  VARCHAR(255), --check on this
    union_membership            BOOLEAN,
    union_id                    UUID REFERENCES organization (id),
    professional_membership_id  UUID REFERENCES organization (id),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS person_organization (
    id                          UUID PRIMARY KEY,
    person_id                   UUID NOT NULL REFERENCES person (id),
    organization_id             UUID NOT NULL REFERENCES organization (id),
    relationship_type           VARCHAR(255),  --controlled vocabulary
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS comp_creds (
    id                          UUID PRIMARY KEY,
    type                        comp_cred_type NOT NULL,
    identifier                  VARCHAR(100),
    identifier_url              TEXT,
    taxonomy_id                 VARCHAR(100),
    valid_start_date            DATE,
    valid_end_date              DATE,
    parent_id                   VARCHAR(100),
    parent_url                  TEXT,
    parent_code                 VARCHAR(100),
    code                        VARCHAR(100),
    type_url                    TEXT,
    statement                   TEXT,
    framework_title             VARCHAR(100) NOT NULL,
    framework_version           VARCHAR(100),
    framework_identifier        VARCHAR(100),
    framework_description       TEXT,
    framework_subject           VARCHAR(100),
    framework_valid_start_date  DATE,
    framework_valid_end_date    DATE,
    record_status               VARCHAR(10),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS person_comp_cred (
    employment_record_id        UUID NOT NULL REFERENCES person (id),
    comp_cred_id                UUID NOT NULL REFERENCES comp_creds (id),
    hasRecord                   BOOLEAN,
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS learning_resource (
    id                          UUID PRIMARY KEY,
    iri                         VARCHAR(300) NOT NULL UNIQUE,
    title                       VARCHAR(300) NOT NULL,
    subject_matter              VARCHAR(100),
    subject_abbreviation        VARCHAR(20),
    level                       VARCHAR(50),
    number                      VARCHAR(50),
    instruction_method          VARCHAR(50),
    start_date                  DATE,
    end_date                    DATE,
    provider_name               VARCHAR(100),
    department_name             VARCHAR(100),
    grade_scale_code            VARCHAR(50),
    metadata_repository         VARCHAR(50),
    lrsendpoint                 VARCHAR(50),
    description                 TEXT,
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS learning_record (
    id                          UUID PRIMARY KEY,
    person_id                   UUID NOT NULL REFERENCES person (id),
    learning_resource_id        UUID NOT NULL REFERENCES learning_resource (id),
    enrollment_date             DATE,
    record_status               learning_status NOT NULL,
    academic_grade              VARCHAR(50),
    event_time                  TIMESTAMP WITH TIME ZONE,
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS phone (
    id                          UUID PRIMARY KEY,
    telephone_number            VARCHAR(255),
    telephone_number_type       VARCHAR(255), --enum
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS person_phone (
    id                          UUID PRIMARY KEY,
    person_id                   UUID NOT NULL REFERENCES person (id),
    phone_id                    UUID NOT NULL REFERENCES phone (id),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS email (
    id                          UUID PRIMARY KEY,
    email_address               VARCHAR(255),
    email_address_type          VARCHAR(255), --enum
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS person_email (
    id                          UUID PRIMARY KEY,
    person_id                   UUID NOT NULL REFERENCES person (id),
    email_id                    UUID NOT NULL REFERENCES email (id),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE                 
);

CREATE TABLE IF NOT EXISTS facility (
    id                          UUID PRIMARY KEY,
    name                        VARCHAR(255),
    location_id                 UUID REFERENCES location (id),
    description                 TEXT,
    operational_status          VARCHAR(255), --enum
    facility_security_level     VARCHAR(255), --enum?
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS organization_facility (
    id                          UUID PRIMARY KEY,
    facility_id                 UUID NOT NULL REFERENCES facility (id),
    organization_id             UUID NOT NULL REFERENCES organization (id),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS employment_record (
    id                          UUID PRIMARY KEY,
    employer_organization       UUID NOT NULL REFERENCES organization (id),
    employee                    UUID NOT NULL REFERENCES person (id),
    employee_id                 VARCHAR(255),
    custom_employment_record_id VARCHAR(255),
    employee_type               VARCHAR(255), --enum/controlled?
    hire_date                   DATE,
    hire_type                   VARCHAR(255), --enum/controlled?
    employment_start_date       DATE,
    employment_end_date         DATE,
    position                    VARCHAR(255),
    position_title              VARCHAR(255),
    position_description        TEXT,
    job_level                   VARCHAR(255),
    occupation                  VARCHAR(255),
    employment_location         UUID REFERENCES location (id),
    employment_facility         UUID REFERENCES facility (id),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS employment_comp_cred (
    employment_record_id        UUID NOT NULL REFERENCES person (id),
    comp_cred_id                UUID NOT NULL REFERENCES comp_creds (id),
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS military_record (
    id                          UUID PRIMARY KEY,
    person_id                   UUID NOT NULL REFERENCES person (id),
    branch                      VARCHAR(100) NOT NULL, -- ENUM? CONTROLLED?
    country                     VARCHAR(100) NOT NULL, -- VALIDATED?
    induction_date              DATE,
    induction_rank              VARCHAR(100),
    release_date                DATE,
    current_rank                VARCHAR(100),
    current_status              military_status NOT NULL,
    discharge_date              DATE,
    discharge_category          VARCHAR(100),
    discharge_rank              VARCHAR(100),
    highest_rank                VARCHAR(100),
    military_id                 VARCHAR(100),
    -- jobs: needs clarification
    -- duties: needs clarification
    -- honors: needs clarification
    -- disciplinaryAction: needs clarification
    -- skill                       
    -- expertise
    updated_by                  VARCHAR(20),
    inserted_date               TIMESTAMP WITH TIME ZONE,
    last_modified               TIMESTAMP WITH TIME ZONE
);
