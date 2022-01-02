package org.dalvarez.ddd_example.shared.infrastructure.bus;

import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEventSubscriber;
import org.reflections.Reflections;

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
        Reflections reflections = new Reflections(PACKAGE_BASE);
        Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(DomainEventSubscriber.class);

        return subscribers.stream()
                          .map(subscriberClass -> {
                              Class<? extends DomainEvent>[] domainEvents = subscriberClass.getAnnotation(
                                                                                                   DomainEventSubscriber.class)
                                                                                           .value();
                              return new DomainEventSubscriberInformation(
                                      subscriberClass,
                                      Arrays.asList(domainEvents)
                              );
                          })
                          .collect(Collectors.toList());
    }

    public List<DomainEventSubscriberInformation> all() {
        return information;
    }

}
