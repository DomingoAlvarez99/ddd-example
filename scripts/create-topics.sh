#!/bin/bash

topics=$1
partitions=$2

IFS=',' read -r -a topics_array <<< "$topics"

for topic in "${topics_array[@]}"; do
  docker exec \
    -e TOPIC="$topic" \
    -e PARTITIONS="${partitions:=1}" \
    kafka \
    bash \
    -c 'kafka-topics \
          --create \
          --if-not-exists \
          --topic "$TOPIC" \
          --partitions "$PARTITIONS" \
          --replication-factor 1 \
          --zookeeper "$KAFKA_ZOOKEEPER_CONNECT"'
done