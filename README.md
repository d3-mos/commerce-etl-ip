# IP Geolocation ETL

This architectural component represents the ETL process to fetch databases with "IP - Geolocation" relation registers. The goal of this component is provide the data for make available a non-intrusive method to locate an user by your device IP. At this time this project use two "IP-Geolocation" databases: Geolite and IP2Location.

## 1. Requirements

The next tools are needs to run this project, install all tools according with the O.S.:

 - [Apache Maven 3.6.2](https://maven.apache.org/)
 - [Java 1.8.*](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - IDE Text Editor
 - [IP2Location API key](https://lite.ip2location.com/sign-up?ref=DB1)
 - [Docker >=18.03.0-ce](https://runnable.com/docker/getting-started/)

## 2. Installation

#### 2.1 Environtment set up

If you have a UNIX environment, you can run the next commands over terminal:

```bash
  # Go into enviroment settings folder.
  cd src/main/resources/env

  # Set development environment
  cp config.example.properties config.development.properties

  # Set production environment
  cp config.example.properties config.production.properties
```

At end, set the environment properties replacing the values with custom credentials.

#### 2.2 Project performance

There are many ways to run this project. If you have the requirements, then over the main path you run the next commands to reach the application performance:

```bash
mvn clean

mvn package -P <environment>

mvn exec:java -Dexec.cleanupDaemonThreads=false
```

There are three options to environment tag: development, staging and production, according with one or other run the project with environment configuration.

#### 2.3 Build docker image

You must follow the next steps to build and run an container with the ETL java application:

```bash
#Build application
mvn package -P <environment>

#Build image
docker build . -t commerce--etl:<environment>

#Run image. If you using internal containers, set --link parameters in other
# case drop "--link" lines.
docker run \
  -it --rm \
  --name commerce-etl-ip \
  --link <claro pay DB container>:commerce-db-claropay \
  commerce-etl-ip:<environment>
```

## 3. Other links and tutorials

Maven project:

 - [Maven profiles](https://www.mkyong.com/maven/maven-profiles-example/)

Docker images to java project:

 - [Crafting the perfect Java Docker build flow](https://codefresh.io/docker-tutorial/java_docker_pipeline/)

IP2Location documentation:

 - [IP2Location main page](https://www.ip2location.com/)
 - [IP2Location docker hub image](https://hub.docker.com/r/ip2location/mysql/)
 - [IP2Location database](https://lite.ip2location.com/database/ip-country-region-city-latitude-longitude-zipcode)

Geolite documentation:

 - [Geolite main page](https://dev.maxmind.com/geoip/geoip2/geolite2/)

## 4. Developers team.

 Software Developer: Ricardo Bermúdez Bermúdez
 - E-mail:       rbermudez@ndscognitivelabs.com
 - Mobile phone: 5548879549    
 - Job title:    Full Stack Software Engineer
