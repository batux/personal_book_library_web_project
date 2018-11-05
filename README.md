# Personal Book Library Web Project 

# Architecture

<img src="https://user-images.githubusercontent.com/2838457/48023501-035ea380-e14f-11e8-8747-b374f3c3cdd1.png">

# Presentation

https://github.com/batux/personal_book_library_web_project/blob/master/com.personal.book.library/AngularJS_JavaTechnologies.pdf

# Technologies:

- Java
- JUnit
- MySQL
- MongoDB
- Redis
- Bootstrap CSS
- AngularJS
- Docker
- Google Recaptcha
- Spring Framework
- Spring Core
- Spring Data
- Spring MVC
- Spring Kafka
- Spring Security
- Spring Session
- Spring Redis

# Google reCAPTCHA must Changes!

You should create some recaptcha keys from https://www.google.com/recaptcha/admin#list for your application. And then, you should replace PUT_YOUR_KEY symbols with your keys.

#Google Captcha v3
google.captcha.site.key=PUT_YOUR_KEY
google.captcha.secret.key=PUT_YOUR_KEY

# Docker Compose File for Infrastructure

Run these docker commands

```docker
# create docker-machine
docker-machine create --driver virtualbox --virtualbox-memory 6000 kafka

# get docker machine ip
docker-machine ip kafka

# Docker compose up for Infrastructure
docker-compose -f docker_compose_app_kafka.yml up

# list all active docker containers
docker ps -a
```

Than you can create a docker compose yml file. You can find it as an example in my repo!

```yml
version: '2'
services:

  mysql:
    image: mysql:latest
    ports: 
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root
    volumes: 
      - ~/mysql_data:/var/lib/mysql

  mongodb:
    image: mongo:latest
    ports:
      - "27017:27017"
    volumes:
      - ~/mongodb_data:/data/db mongo

  redis:
    image: redis:latest
    ports:
      - "6379:6379"

  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka:0.10.2.0-1
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1 #192.168.99.100 #{Docker Machine IP}
      KAFKA_ADVERTISED_PORT: 9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

Application screenshots

<img src="https://user-images.githubusercontent.com/2838457/48023253-49673780-e14e-11e8-9036-90218f6490b6.png" width="700" height="400">

<img src="https://user-images.githubusercontent.com/2838457/46903411-13cb8780-cedd-11e8-9f71-5c79ffe74e00.png" width="700" height="400">

