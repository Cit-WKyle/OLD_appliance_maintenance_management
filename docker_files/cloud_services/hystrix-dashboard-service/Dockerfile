FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.d.m.williamson@gmail.com>

ADD ./hystrix-dashboard-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hystrix-dashboard-service-0.0.1.jar"]

EXPOSE 8102
