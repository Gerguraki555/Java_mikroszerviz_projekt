FROM openjdk:21
ENV LANG C.UTF-8

RUN mkdir -p /opt
ADD maven/opt/Gerguraki.jar /opt/Gerguraki.jar


EXPOSE 8080

ENTRYPOINT java -jar /opt/Gerguraki.jar -Djava.net.preferIPv4Stack=true