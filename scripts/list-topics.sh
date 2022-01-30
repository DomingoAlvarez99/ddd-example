#!/bin/bash

docker exec \
  kafka \
  bash \
  -c 'kafka-topics \
        --list \
        --zookeeper "$KAFKA_ZOOKEEPER_CONNECT"'