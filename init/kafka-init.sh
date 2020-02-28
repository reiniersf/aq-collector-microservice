#!/bin/bash

kafka-server-start.sh /opt/brokers/bkr-r0.properties --override broker.id=${BROKER_ID} --override listeners=PLAINTEXT://${HOSTNAME}:${LISTENER_PORT}
