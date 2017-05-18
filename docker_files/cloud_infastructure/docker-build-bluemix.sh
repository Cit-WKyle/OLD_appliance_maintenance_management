cf ic login

export DOCKER_HOST=tcp://containers-api.eu-gb.bluemix.net:8443
export DOCKER_CERT_PATH=/Users/kyle_williamson/.ice/certs/containers-api.eu-gb.bluemix.net/89893d0c-623d-455b-a37c-7cfadc649b89
export DOCKER_TLS_VERIFY=1

cd account-service
docker build -t account-service ./

cd ../analytics-web-edge-service
docker build -t analytics-web-edge-service ./

cd ../analytics-web-portal-service
docker build -t analytics-web-portal-service ./

cd ../android-edge-service
docker build -t android-edge-service ./

cd ../appliance-service
docker build -t appliance-service ./

cd ../appliance-status-notification-service
docker build -t appliance-status-notification-service ./

cd ../appliance-status-service
docker build -t appliance-status-service ./

cd ../config-service
docker build -t config-service ./

cd ../discovery-service
docker build -t discovery-service ./

cd ../hystrix-dashboard-service
docker build -t hystrix-dashboard-service ./

cd ../property-appliance-service
docker build -t property-appliance-service ./

cd ../property-service
docker build -t property-service ./

cd ../turbine-stream-service
docker build -t turbine-stream-service ./

cd ..

cf ic cpi postgres registry.eu-gb.bluemix.net/dev_docker/postgres
cf ic cpi rabbitmq registry.eu-gb.bluemix.net/dev_docker/rabbitmq