package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public final class KafkaConfigProvider {

    private static final String ADDRESS_DELIMITER = ":";

    private final String kafkaHost;

    private final String kafkaPort;

    public KafkaConfigProvider(@Value("${kafka.host}") final String kafkaHost,
                               @Value("${kafka.port}") final String kafkaPort) {
        this.kafkaHost = kafkaHost;
        this.kafkaPort = kafkaPort;
    }


    public String bootstrapAddress() {
        return String.join(ADDRESS_DELIMITER, kafkaHost, kafkaPort);
    }

}
