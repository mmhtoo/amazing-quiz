FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/quizz-0.0.1-SNAPSHOT.jar ./app.jar
ENV DB_USERNAME=root
ENV DB_PASSWORD=root@123

CMD ["java","-jar","app.jar"]