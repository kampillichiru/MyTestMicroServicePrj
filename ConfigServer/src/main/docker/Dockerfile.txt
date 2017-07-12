FROM azul/zulu-openjdk:8
MAINTAINER Orlando L Otero <ootero@asimio.net>, https://bitbucket.org/asimio/configserver.git

VOLUME /tmp

ADD config-server.jar /opt/asimio-cloud/config-server.jar
RUN bash -c 'touch /opt/asimio-cloud/config-server.jar'

ENV JAVA_TOOL_OPTIONS "-Xms256M -Xmx256M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["java", "-jar", "/opt/asimio-cloud/config-server.jar"]

# No need to EXPOSE port since it's using host's networking

# Run as:
# docker run -idt -p 8101:8101 --net=host -e appPort=8101 -e hostName=$HOSTNAME -e eureka.client.serviceUrl.defaultZone="http://$HOSTNAME:8001/eureka/,http://$HOSTNAME:8002/eureka/" -e git.username=<username> -e git.passwd=<passwd> asimio/config-server:latest
# curl -v "http://localhost:8101/poc_1/dev"