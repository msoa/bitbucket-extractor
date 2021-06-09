FROM openjdk:11.0.8-slim-buster
RUN mkdir /opt/app
COPY build/libs/bitbucket-extractor*.jar /opt/app/bitbucket-extractor.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/bitbucket-extractor.jar"]
