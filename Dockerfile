FROM maven:3.8-jdk-11
COPY src  home/seleniumframework/src
COPY pom.xml	home/seleniumframework/pom.xml
COPY testng.xml	home/seleniumframework/testng.xml
WORKDIR home/seleniumframework
ENTRYPOINT mvn clean test