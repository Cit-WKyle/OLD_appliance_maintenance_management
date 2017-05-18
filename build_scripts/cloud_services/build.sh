#!/bin/bash
rm -r ../../build
mkdir ../../build

rm -r ../../cloud_services/analytics-web-portal-service/src/main/resources/static
mkdir ../../cloud_services/analytics-web-portal-service/src/main/resources/static

rm -r ../../web_application/dist
mkdir ../../web_application/dist

cd ../../web_application
npm run build
cd ~/Dev/appliance_maintenance_management/build_scripts/cloud_services
mv ../../web_application/dist/* ../../cloud_services/analytics-web-portal-service/src/main/resources/static
mvn package -Dmaven.test.skip=true
