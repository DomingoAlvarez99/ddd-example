#!/bin/bash

while ! nc -z $(tr ':' ' ' <<<"$KAFKA_ZOOKEEPER_CONNECT"); do sleep 3; done

bash /etc/confluent/docker/run