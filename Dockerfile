FROM maven:latest

ENV SPRING_DATA_MONGODB_URI=mongodb://mongo-db-client:27017/fiap-lanches-client
WORKDIR /app
RUN rm -rf /app/*
COPY . /app
RUN mvn clean install -DskipTests
RUN mkdir jar
RUN mv /app/target/fiap-lanches-client-0.0.1-SNAPSHOT.jar /app/jar/fiap-lanches-client-app.jar
EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/jar/fiap-lanches-client-app.jar"]
