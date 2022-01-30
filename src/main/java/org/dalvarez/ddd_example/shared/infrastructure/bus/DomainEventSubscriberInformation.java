package org.dalvarez.ddd_example.shared.infrastructure.bus;

import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;

import java.util.List;

public final class DomainEventSubscriberInformation {

    private final Class<?> subscriberClass;

    private final List<SubscribedEvent> subscribedEvents;

    public DomainEventSubscriberInformation(final Class<?> subscriberClass,
                                            final List<SubscribedEvent> subscribedEvents) {
        this.subscriberClass = subscriberClass;
        this.subscribedEvents = subscribedEvents;
    }

    public Class<?> subscriberClass() {
        return subscriberClass;
    }

    public SubscribedEvent subscribedEvent() {
        return subscribedEvents.get(0);
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    @Override
    public String toString() {
        return "DomainEventSubscriberInformation{" +
                "subscriberClass=" + subscriberClass +
                ", subscribedEvents=" + subscribedEvents +
                '}';
    }

    public static class SubscribedEvent {

        private final Class<? extends DomainEvent> clazz;

        private final String name;

        public SubscribedEvent(final Class<? extends DomainEvent> clazz,
                               final String subscribedEventName) {
            this.clazz = clazz;
            this.name = subscribedEventName;
        }

        public Class<? extends DomainEvent> clazz() {
            return clazz;
        }

        public String name() {
            return name;
        }

    }

}
