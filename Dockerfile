#build
FROM maven:4.0.0-rc-5-amazoncorretto-25-al2023 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

#Create Image
FROM amazoncorretto:latest

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]