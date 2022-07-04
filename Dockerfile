FROM openjdk:16-alpine3.13

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN mkdir -p /var/www/html/foo
CMD ["./mvnw", "spring-boot:run"]