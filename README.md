# Personal Book Library Web Project 

# Presentation

https://github.com/batux/personal_book_library_web_project/blob/master/com.personal.book.library/AngularJS_JavaTechnologies.pdf

# Technologies:

- Java
- JUnit
- MySQL
- MongoDB
- Bootstrap CSS
- AngularJS
- Docker
- Spring Framework
- Spring Core
- Spring Data
- Spring MVC
- Spring Security

# Docker for Kafka & Zookeeper

```docker
# create docker-machine
docker-machine create --driver virtualbox --virtualbox-memory 6000 kafka

# get docker machine ip
docker-machine ip kafka

# Docker compose up for Kafka & Zookeeper
docker-compose -f docker_compose_app_kafka.yml up

# list all active docker containers
docker ps -a
```

```yml
version: '2'
services:
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

# Docker Commands

```docker
docker pull mysql
docker run --name mysqltest -p 3306:3306 -v ~/mysql_data:/var/lib/mysql  -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

docker pull mongo
docker run -d -p 27017:27017 -v ~/mongodb_data:/data/db mongo
```


<img src="https://user-images.githubusercontent.com/2838457/46903382-ae779680-cedc-11e8-932e-d578c889aae2.png" width="700" height="400">

<img src="https://user-images.githubusercontent.com/2838457/46903411-13cb8780-cedd-11e8-9f71-5c79ffe74e00.png" width="700" height="400">

