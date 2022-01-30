package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.publisher;

import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class KafkaEventBus extends KafkaPublisher<DomainEvent> implements EventBus {

    private static final String TOPIC = "domain_events";

    public KafkaEventBus(final KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent event) {
        publish(TOPIC, event);
    }

}
