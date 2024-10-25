
# elrrservices
Elrr services which provide a mechanism to synchronize the data in the ELRR with the data in the local learning systems

Setup elrrdatasync first [README](../elrrdatasync/README.md)

# Dependencies
- Java JDK 17
- git
- Maven 3
- Docker Desktop

# Tools
- DBeaver
- Eclipse or other IDE

# Build the application
- mvn clean install

# Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.deloitte.elrr.DemoApplication class from your IDE

# Alternatively you can use the Spring Boot Maven plugin: 
- mvn clean
- mvn spring-boot:run -D"spring-boot.run.profiles"=local -e (Windows)
- mvn spring-boot:run -D spring-boot.run.profiles=local -e (Linux)
- Ctrl+C to end --> Terminate batch job = Y

# Optional step 
1. docker push <docker_hub>/test:elrrservices-dck-img