# Notable API Parameters

## Person

### GET /api/person

The GET /api/person endpoint supports the following optional query parameters for filtering persons:

#### Basic Filters
- **id** (UUID[]): Filter by specific person IDs
- **ifi** (String[]): Filter by IFIs (Inverse Functional Identifiers)
- **name** (String[]): Filter by Name, case-insensitive. The wildcard character `%` can be used like `% Smith`
- **emailAddress** (String[]): Filter by email addresses, case-insensitive. The wildcard character `%` can be used like `%@example.com`.
- **phoneNumber** (String[]): Filter by phone numbers, case-insensitive. Ignores all characters except digits.

#### Organization Filters  
- **associatedOrgId** (UUID[]): Filter by organization IDs via association relationships
- **employerOrgId** (UUID[]): Filter by organization IDs via employment relationships

#### Location Filters
- **locationId** (UUID[]): Filter by location IDs present in any location field - returns persons who have any of the specified location IDs in any of their address or location relationships
    - Searches across all location fields: mailing address, physical address, shipping address, billing address, on-campus address, off-campus address, temporary address, permanent student address, employment address, time of admission address, father address, mother address, guardian address, and birthplace

#### Extension Filters
- **hasExtension** (String[]): Filter for persons who have all specified extension keys present in their extensions JSON
- **extensionPath** (String[]): Filter using JSONPath expressions with the `@?` operator - returns persons where all specified paths exist in their extensions JSON
    - Example: `$."https://example.com/clr/achievementType"`
    - Example: `$."https://example.org/openbadges/evidence".url`
    - Example: `$."https://example.net/person/birthDate"`

- **extensionPathMatch** (String[]): Filter using JSONPath predicates with the `@@` operator - returns persons where all specified predicates match in their extensions JSON
    - Example: `$."https://example.com/clr/achievementType" == "Certificate"`
    - Example: `$."https://example.net/person/birthDate" > "1990-01-01"`
    - Example: `$."https://example.org/openbadges/evidence".status == "verified"`
    - Example: `$."https://example.com/clr/creditsEarned" >= 30`

#### Relation Filters
- **competencyId** (UUID[]): Filter by competency IDs via qualification relationships
- **credentialId** (UUID[]): Filter by credential IDs via qualification relationships
- **learningResourceId** (UUID[]): Filter by learning resource IDs via learning record relationships