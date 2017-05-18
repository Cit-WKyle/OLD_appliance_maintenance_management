FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.d.m.williamson@gmail.com>

ADD ./appliance-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/appliance-service-0.0.1.jar"]

EXPOSE 8402
