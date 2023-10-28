FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install git -y

RUN git clone https://github.com/nicrodriguezval/technical-test.git
RUN cd technical-test

WORKDIR /technical-test

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
