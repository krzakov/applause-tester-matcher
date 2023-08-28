# Build Spring Boot backend

FROM amazoncorretto:17.0.6
COPY build/libs/*.jar applause-matcher.jar
ENTRYPOINT ["java","-jar","/applause-matcher.jar"]
