FROM openjdk:17-oracle

VOLUME /tmp

RUN  mkdir -p /var/log/csms
RUN  chmod -R 777 /var/log/csms

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]