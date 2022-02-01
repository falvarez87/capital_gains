FROM openjdk:11

EXPOSE 8080

RUN mkdir /app

COPY build/libs/*.jar /app/capital_gains.jar

ENTRYPOINT ["java", "-jar","/app/capital_gains.jar"]