FROM maven:3.8-openjdk-17 AS build
#WORKDIR /demo
COPY . .
RUN mvn clean package -Dskiptests
ONBUILD RUN mvn install

FROM openjdk:17.0.1-jdk-slim
#WORKDIR /demo/src
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
#FROM bfg/api-java8-maven-exp-srv-builder
#EXPOSE 8080
#
#RUN mkdir -p /usr/src/app
#WORKDIR /usr/src/app
#ONBUILD ADD . /usr/src/app
#ONBUILD RUN mvn install
#ONBUILD ADD /usr/src/app/target/springbootdemo-0.0.1-SNAPSHOT.jar app.jar
#
#CMD ["java","-jar","/app.jar"]
