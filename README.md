
# elrrservices
Elrr services which provide a mechanism to synchronize the data in the ELRR with the data in the local learning systems

There are database and kafka dependencies, but there's a [repo with a docker-compose](https://github.com/US-ELRR/elrrdatasync/) that resolves them locally.

[Setup elrrdatasync first](https://github.com/US-ELRR/elrrdatasync/)

# Dependencies
- [Java JDK 1.8](https://www.oracle.com/java/technologies/downloads/)
- [git](https://git-scm.com/downloads)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [PostgreSQL](https://www.postgresql.org/download/)

# Tools
- SQL client or Terminal
- [Postman](https://www.postman.com/downloads/)
- [Eclipse](https://www.eclipse.org/downloads/packages/) or other IDE

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
- docker push <docker_hub>/test:elrrservices-dck-img