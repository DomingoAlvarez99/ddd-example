package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;

public abstract class KafkaPublisher<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    protected KafkaPublisher(final KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    protected void publish(final String topic,
                           final String key,
                           final Integer partition,
                           final T message) {
        kafkaTemplate.send(topic, partition, key, message);
    }

    protected void publish(final String topic,
                           T message) {
        kafkaTemplate.send(topic, 0, message.getClass().getName(), message);
    }

}
