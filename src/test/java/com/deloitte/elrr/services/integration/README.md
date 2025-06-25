# Integration Tests with TestContainers

This directory contains integration tests for the ELRR Services project using TestContainers to provide a PostgreSQL database for testing entity persistence and behavior.

## Overview

The integration tests are designed to test:
- Entity persistence and retrieval
- Entity relationships and foreign key constraints
- Cascade operations (DELETE CASCADE behavior)
- Enum type handling (e.g., LearningStatus, QualificationType)
- Database constraints and validation
- Complex entity behavior and business logic

## Test Structure

### Base Test Class
- `BaseIntegrationTest`: Provides common TestContainers setup and configuration

### Entity Integration Tests
- `PersonIntegrationTest`: Tests Person entity and Location relationships
- `OrganizationIntegrationTest`: Tests Organization entity persistence and validation
- `AssociationIntegrationTest`: Tests Person-Organization associations and cascade behavior
- `QualificationIntegrationTest`: Tests Competency, Credential, and personal qualification entities
- `LearningRecordIntegrationTest`: Tests LearningRecord, LearningResource, and enum handling

## TestContainers Configuration

The tests use a PostgreSQL 15.4 container with:
- Database name: `elrr_test`
- Username: `testuser`
- Password: `testpass`
- Schema initialization: `test-schema.sql` (matches production schema structure)

## Running the Tests

### Prerequisites
- Docker must be installed and running
- Maven 3.6+ 
- Java 17+

### Running All Integration Tests
```bash
# From the elrrservices directory
mvn test -Dtest="**/*IntegrationTest"
```

### Running Specific Test Classes
```bash
# Run Person integration tests
mvn test -Dtest="PersonIntegrationTest"

# Run Organization integration tests  
mvn test -Dtest="OrganizationIntegrationTest"

# Run Association integration tests
mvn test -Dtest="AssociationIntegrationTest"
```

### Running Tests in IDE
1. Ensure Docker is running
2. Run individual test classes or methods directly from your IDE
3. The TestContainers will automatically start a PostgreSQL container

## Test Features

### Database Schema Testing
- Tests use the same schema structure as production (`test-schema.sql`)
- Validates entity mappings against actual database constraints
- Tests database-level features like generated columns and enums

### Relationship Testing
- Tests JPA relationships (OneToMany, ManyToOne, etc.)
- Validates cascade operations (ON DELETE CASCADE)
- Tests entity association and disassociation

### Enum Handling
- Tests custom enum types (LearningStatus)
- Validates enum persistence and retrieval
- Tests enum constraints and validation

### Transaction Testing
- All tests are transactional and roll back after completion
- Tests isolation and data consistency
- Validates entity state management

## Dependencies

The integration tests use:
- TestContainers JUnit Jupiter
- TestContainers PostgreSQL
- Spring Boot Test
- AssertJ for fluent assertions
- JUnit 5

## Test Database Schema

The test database uses the same schema as production:
- Schema: `services_schema`
- All tables, constraints, and types from production
- Proper foreign key relationships and cascade rules
- Custom enum types for learning status and qualification types

## Best Practices

1. **Isolation**: Each test method is isolated with `@DirtiesContext`
2. **Transactions**: All tests are `@Transactional` and rollback automatically
3. **Realistic Data**: Tests use realistic test data that matches business rules
4. **Comprehensive Coverage**: Tests cover happy path, edge cases, and error conditions
5. **Performance**: Tests are optimized for reasonable execution time

## Troubleshooting

### Docker Issues
- Ensure Docker is running and accessible
- Check Docker permissions for your user account
- Verify sufficient disk space for container images

### Test Failures
- Check entity field mappings match database schema
- Verify relationship annotations are correct
- Check for missing or incorrect database constraints

### Performance Issues
- Consider using `@Testcontainers` with `@Container` static fields for shared containers
- Use connection pooling for better performance
- Optimize test data creation methods
