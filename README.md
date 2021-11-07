1. Download or copy the contents of the Confluent Platform all-in-one Docker Compose file

        curl --silent --output docker-compose.yml \
        https://raw.githubusercontent.com/confluentinc/cp-all-in-one/7.0.0-post/cp-all-in-one/docker-compose.yml


2. Start the Confluent Platform stack with the -d option to run in detached mode:

        docker-compose up -d


KSQLDBServer http://ksqldb-server:8088 

Zookeeper  http://localhost:2391

Kafka http://localhost:9092

Schema Registry http://localhost:8081

Control Center http://localhost:9021



![Screenshot from 2021-11-07 22-46-33](https://user-images.githubusercontent.com/18550930/140658033-238279a1-b6da-458b-896d-7c77c2904368.png)


![Screenshot from 2021-11-07 22-45-48](https://user-images.githubusercontent.com/18550930/140658146-b42bb60d-cb40-404e-aca1-9f582c8b52a7.png)
