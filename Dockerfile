FROM docker.io/library/maven:3.8.7 AS build
WORKDIR /app

# Copy the pom.xml file
COPY ./pom.xml /app/pom.xml

# Copy the source code
COPY ./src /app/src

# Package the application
#RUN mvn -f /app/pom.xml clean package -DskipTests
RUN mvn -f /app/pom.xml dependency:resolve
RUN mvn -f /app/pom.xml clean install


# Stage 2: Create the runtime image
FROM docker.io/eclipse-temurin:17-jre
WORKDIR /app

# Copy the packaged application from the builder stage
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

