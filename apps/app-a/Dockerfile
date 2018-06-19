FROM openjdk:8-alpine
MAINTAINER name <email>

ADD target/app.jar /srv/service.jar

EXPOSE 443
VOLUME "/var/TEMPLATE-APP/certs/"

ENV env DOCKER
CMD ["java", "-jar", "/srv/service.jar"]
