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