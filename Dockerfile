FROM openjdk:8-jdk-alpine
LABEL maintainer="My Retail Product Services "

VOLUME /temp
EXPOSE 50502
ARG JAR_FILE=build/libs/*.jar

ADD ${JAR_FILE} myretail_product_services.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myretail_product_services.jar"]