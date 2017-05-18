FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.d.m.williamson@gmail.com>

ADD ./turbine-stream-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/turbine-stream-service-0.0.1.jar"]

EXPOSE 8103
EXPOSE 8300
EXPOSE 8301
