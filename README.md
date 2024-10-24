
# elrrservices API
Elrr services which provide a mechanism to synchronize the data in the ELRR with the data in the local learning systems 

# Dependencies
- Java JDK 17
- git
- Maven 3
- Docker Desktop
- DBeaver
- Postman

# Run elrrservices
- Update application-local.properties to match docker-compose.yml
- Start Docker Desktop
- Open terminal
- git switch <dev feature branch>
- mvn clean
- mvn spring-boot:run -D"spring-boot.run.profiles"=local -e (Windows)
- mvn spring-boot:run -D spring-boot.run.profiles=local -e (Linux)
- Ctrl+C to end --> Terminate batch job = Y