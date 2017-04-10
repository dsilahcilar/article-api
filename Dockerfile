FROM maven:3.3.3-jdk-8
ADD . /src
WORKDIR /src
RUN mvn clean package
EXPOSE 8080
CMD java -jar /src/target/*.jar