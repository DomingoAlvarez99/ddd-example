#!/bin/bash

topic=$1
partitions=$2

docker exec \
  -e TOPIC="$topic" \
  -e PARTITIONS="$partitions" \
  kafka \
  bash \
  -c 'kafka-topics \
        --alter \
        --topic "$TOPIC" \
        --partitions "$PARTITIONS" \
        --zookeeper "$KAFKA_ZOOKEEPER_CONNECT"'
