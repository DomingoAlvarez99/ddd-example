package org.dalvarez.ddd_example.shared.infrastructure.bus;

import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEventSubscriber;
import org.dalvarez.ddd_example.shared.infrastructure.bus.DomainEventSubscriberInformation.SubscribedEvent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class DomainEventSubscribersInformation {

    private static final String PACKAGE_BASE = "org.dalvarez.ddd_example";

    private final List<DomainEventSubscriberInformation> information;

    public DomainEventSubscribersInformation(List<DomainEventSubscriberInformation> information) {
        this.information = information;
    }

    public DomainEventSubscribersInformation() {
        this(scanDomainEventSubscribers());
    }

    private static List<DomainEventSubscriberInformation> scanDomainEventSubscribers() {
        final Reflections reflections = new Reflections(PACKAGE_BASE);
        final Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(DomainEventSubscriber.class);

        return subscribers.stream().map(subscriberClass -> {
            final Class<? extends DomainEvent>[] domainEventSubscribers = subscriberClass.getAnnotation(
                    DomainEventSubscriber.class).value();
            final List<SubscribedEvent> subscribedEvents = Arrays.stream(domainEventSubscribers)
                                                                 .map(DomainEventSubscribersInformation::mapToSubscribedEvent)
                                                                 .collect(Collectors.toList());

            return new DomainEventSubscriberInformation(subscriberClass, subscribedEvents);
        }).collect(Collectors.toList());
    }

    private static SubscribedEvent mapToSubscribedEvent(final Class<? extends DomainEvent> domainEvent) {
        try {
            final DomainEvent nullInstance = domainEvent.getConstructor().newInstance();
            final String eventName = nullInstance.eventName();

            return new SubscribedEvent(
                    domainEvent,
                    eventName
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DomainEventSubscriberInformation> all() {
        return information;
    }

    public DomainEventSubscriberInformation get(final Class<?> expectedEventClass) {
        return information.stream()
                          .filter(information -> expectedEventClass.equals(information.subscribedEvent().clazz()))
                          .findFirst()
                          .orElseThrow();
    }

}
