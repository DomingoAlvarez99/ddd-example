package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared.KafkaSharedConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public final class DomainEventDeserializer extends ObjectMapperDeserializer<DomainEvent> {

    public DomainEventDeserializer(@Qualifier(KafkaSharedConfig.OBJECT_MAPPER_BEAN_ID) final ObjectMapper objectMapper) {
        super(DomainEvent.class, objectMapper);
    }

}