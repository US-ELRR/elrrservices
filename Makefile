.phony: launch-dev, clean, debug

clean:
	mvn clean

dev:
	mvn spring-boot:run -D spring-boot.run.profiles=local -e

debug:
	mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000" -D spring-boot.run.profiles=local -e

test:
	mvn test

build:
	mvn install
