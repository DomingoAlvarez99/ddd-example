#!/bin/bash

topic=$1

docker exec \
  -e TOPIC="$topic" \
  kafka \
  bash \
  -c 'kafka-console-consumer \
        --topic $TOPIC \
        --from-beginning \
        --zookeeper "$KAFKA_ZOOKEEPER_CONNECT"'