Applause tester matcher application by Krzysztof Mosek

## Pre-requisites
* Java >= 17 (tested on OpenJDK Runtime Environment `Corretto-17.0.6.10.1 (build 17.0.6+10-LTS`)
* Docker >= 20 installed on your machine (tested on Docker version `20.10.7`, `build f0df350`)
* port `4200` must be available on your machine (angular webapp)
* port `8080` must be available on your machine (spring boot app - swagger documentation)
## How to run
run bash script `run.sh` \
* make sure you have exec access rights
* you run the script from the root of the project
* spring and angular applications will be built and started, it might take few seconds depending on your hardware
## How to test
Open following url in your browser: `http://localhost:4200` \
Play with application as desired

Swagger API documentation: `http://localhost:8080/api/swagger-ui.html` 
