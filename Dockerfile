FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/kotlin/com/settraces/settracesbackend/database/migrations/ migrations/
ENTRYPOINT ["java","-jar","/app.jar"]