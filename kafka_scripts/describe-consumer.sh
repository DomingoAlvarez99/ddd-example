#!/bin/bash

group=$1

docker exec \
  -e $GROUP="group" \
  kafka \
  bash \
  -c 'kafka-consumer-groups \
        --describe \
        --group "$GROUP" \
        --zookeeper "$KAFKA_ZOOKEEPER_CONNECT"'