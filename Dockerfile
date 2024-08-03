FROM shinseiki/maven-openjdk-17:3

WORKDIR /app

COPY src /app/src

COPY pom.xml /app

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/LearningSystem_v2.jar"]
