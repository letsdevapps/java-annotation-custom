FROM maven:latest

WORKDIR /app

COPY pom.xml .
COPY src /app/src

RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar", "target/java-annotation-custom.jar"]