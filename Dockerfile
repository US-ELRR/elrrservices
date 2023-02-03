FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/elrrservices-0.0.1-SNAPSHOT.jar /app
#COPY . .

COPY ./target/dependency/BOOT-INF/lib /app/lib
COPY ./target/dependency/META-INF /app/META-INF
COPY ./target/dependency/BOOT-INF/classes /app

WORKDIR /

ENTRYPOINT ["java","-cp","app:app/lib/*","-Dcom.redhat.fips=false","-Dspring.profiles.active=${ENV}","-Djasypt.encryptor.algorithm=${ALGORITHM}","com.deloitte.elrr.ElrrApplication"]
