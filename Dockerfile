FROM maven:3.6.3-jdk-11 AS MAVEN_TOOL_CHAIN
COPY pom.xml /temp/
WORKDIR /temp/
RUN mvn dependency:go-offline
COPY src /temp/src
RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app/
COPY --from=MAVEN_TOOL_CHAIN /temp/target/game-tracker.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
