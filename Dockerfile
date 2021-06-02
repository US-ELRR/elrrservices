FROM openjdk:15
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ADD ./tmp/testdb.mv.db  ./tmp/testdb.mv.db
ENTRYPOINT ["java","-cp","app:app/lib/*","com.deloitte.elrr.DemoApplication"]