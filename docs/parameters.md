# Notable API Parameters

## Person

### GET /api/person

The GET /api/person endpoint supports the following optional query parameters for filtering persons:

#### Basic Filters
- **id** (UUID[]): Filter by specific person IDs
- **ifi** (String): Filter by IFI (Inverse Functional Identifier)

#### Organization Filters  
- **organizationId** (UUID): Filter by organization ID (via association or employment)
- **organizationRelType** (String): Specify organization relationship type
  - `Association` - Filter by organization associations (default)
  - `Employment` - Filter by employment relationships

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
