FROM amazoncorretto:17.0.6

EXPOSE 8080

VOLUME /tmp
COPY build/libs/*.jar applause-matcher.jar
ENTRYPOINT ["java","-jar","/applause-matcher.jar"]