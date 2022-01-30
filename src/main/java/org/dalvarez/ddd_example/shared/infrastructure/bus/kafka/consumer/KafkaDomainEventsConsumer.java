package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.infrastructure.bus.DomainEventSubscriberInformation;
import org.dalvarez.ddd_example.shared.infrastructure.bus.DomainEventSubscribersInformation;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer.config.KafkaConsumerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
public final class KafkaDomainEventsConsumer<T extends DomainEvent> extends KafkaConsumer {

    private static final String CONSUMER_NAME = "domain_events_consumer";

    private static final String TOPIC = "domain_events";

    private final Map<Class<T>, Object> indexedDomainEventSubscribers = new HashMap<>();

    private final DomainEventSubscribersInformation subscribersInformation;

    private final ApplicationContext applicationContext;

    private final ObjectMapper objectMapper;

    public KafkaDomainEventsConsumer(final KafkaListenerEndpointRegistry endpointRegistry,
                                     final DomainEventSubscribersInformation subscribersInformation,
                                     final ApplicationContext applicationContext,
                                     final ObjectMapper objectMapper) {
        super(endpointRegistry, CONSUMER_NAME);
        this.subscribersInformation = subscribersInformation;
        this.applicationContext = applicationContext;
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("unchecked")
    @KafkaListener(id = CONSUMER_NAME, topics = TOPIC, containerFactory = KafkaConsumerConfig.DEFAULT_CONTAINER_LISTENER_FACTORY_BEAN_ID)
    public void consume(final ConsumerRecord<String, String> consumerRecord) throws ClassNotFoundException, JsonProcessingException {
        final Class<T> eventClass = (Class<T>) Class.forName(consumerRecord.key());
        final T event = objectMapper.readValue(consumerRecord.value(), eventClass);
        final Object subscriber = indexedDomainEventSubscribers.getOrDefault(eventClass, subscriberFor(eventClass));

        try {
            final Method subscriberOnMethod = subscriber.getClass().getMethod("on", event.getClass());
            subscriberOnMethod.invoke(subscriber, event);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(String.format(
                    "The subscriber <%s> should implement a method 'on' listening the domain event <%s>",
                    subscriber.getClass().getName(),
                    event.eventName()
            ));
        }
    }

    private Object subscriberFor(final Class<T> eventClass) {
        final DomainEventSubscriberInformation information = subscribersInformation.get(eventClass);

        final Object subscriber = applicationContext.getBean(information.subscriberClass());
        indexedDomainEventSubscribers.put(eventClass, subscriber);

        return subscriber;
    }

}
