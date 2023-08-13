#
# Build stage
#
FROM maven:3.8.2-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build /target/pawfectielts-0.0.1-SNAPSHOT.jar pawfectielts.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","pawfectielts.jar"]