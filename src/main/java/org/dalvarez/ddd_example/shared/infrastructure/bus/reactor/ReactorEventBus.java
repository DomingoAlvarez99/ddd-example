package org.dalvarez.ddd_example.shared.infrastructure.bus.reactor;

import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.infrastructure.bus.DomainEventSubscribersInformation;
import org.springframework.context.ApplicationContext;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.selector.Selector;
import reactor.fn.Consumer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static reactor.bus.selector.Selectors.$;

public final class ReactorEventBus implements org.dalvarez.ddd_example.shared.domain.bus.EventBus {

    /*
        private final EventBus bus;

        public ReactorEventBus(final ApplicationContext applicationContext,
                               final DomainEventSubscribersInformation subscribersInformation) {
            this.bus = EventBus.create();
            registerEventSubscribers(applicationContext, subscribersInformation);
        }

        private void registerEventSubscribers(final ApplicationContext applicationContext,
                                              final DomainEventSubscribersInformation subscribersInformation) {
            Set<? extends DomainEventSubscriber<? extends DomainEvent>> subscribers = extractDomainEventSubscribers(
                    applicationContext,
                    subscribersInformation
            );
            subscribers.forEach(this::registerOnEventBus);
        }

        private Set<? extends DomainEventSubscriber<? extends DomainEvent>> extractDomainEventSubscribers(final ApplicationContext applicationContext,
                                                                                                          final DomainEventSubscribersInformation subscribersInformation) {
            return subscribersInformation.all()
                                         .stream()
                                         .map(info -> (DomainEventSubscriber<? extends DomainEvent>) applicationContext.getBean(
                                                 info.subscriberClass()))
                                         .collect(Collectors.toSet());
        }

        private void registerOnEventBus(final DomainEventSubscriber<? extends DomainEvent> subscriber) {
            final Selector<?> eventIdentifier = $(subscriber.subscribedTo());

            bus.on(eventIdentifier, eventConsumer(subscriber));
        }

        private <E extends DomainEvent> Consumer<Event<E>> eventConsumer(final DomainEventSubscriber<E> subscriber) {
            return (Event<E> reactorEvent) -> {
                E unwrappedEvent = reactorEvent.getData();
                subscriber.consume(unwrappedEvent);
            };
        }

        @Override
        public void publish(final List<DomainEvent> events) {
            events.forEach(this::publish);
        }

        private void publish(final DomainEvent event) {
            Class<? extends DomainEvent> eventIdentifier = event.getClass();
            Event<DomainEvent> wrappedEvent = Event.wrap(event);

            bus.notify(eventIdentifier, wrappedEvent);
        }
    */
    private final EventBus bus;

    public ReactorEventBus(final ApplicationContext applicationContext,
                           final DomainEventSubscribersInformation subscribersInformation) {
        this.bus = EventBus.create();
        registerEventSubscribers(applicationContext, subscribersInformation);
    }

    private void registerEventSubscribers(final ApplicationContext applicationContext,
                                          final DomainEventSubscribersInformation subscribersInformation) {
        subscribersInformation.all()
                              .forEach(information -> {
                                  final Object subscriber = applicationContext.getBean(information.subscriberClass());
                                  final Class<?> domainEventClass = information.subscribedEvent();

                                  registerOnEventBus(subscriber, domainEventClass);
                              });
    }

    private void registerOnEventBus(final Object subscriber,
                                    final Class<?> domainEventClass) {
        final Selector<?> eventIdentifier = $(domainEventClass);
        final Consumer<Event<?>> c = eventConsumer(subscriber);

        bus.on(eventIdentifier, c);
    }

    private Consumer<Event<?>> eventConsumer(final Object subscriber) {
        return (Event<?> reactorEvent) -> {
            final DomainEvent unwrappedEvent = (DomainEvent) reactorEvent.getData();

            try {
                final Method subscriberOnMethod = subscriber.getClass()
                                                            .getMethod("on", unwrappedEvent.getClass());
                subscriberOnMethod.invoke(subscriber, unwrappedEvent);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException error) {
                throw new RuntimeException(String.format(
                        "The subscriber <%s> should implement a method 'on' listening the domain event <%s>",
                        subscriber.getClass()
                                  .getName(),
                        unwrappedEvent.eventName()
                ));
            }
        };
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent event) {
        final Class<? extends DomainEvent> eventIdentifier = event.getClass();
        final Event<DomainEvent> wrappedEvent = Event.wrap(event);

        bus.notify(eventIdentifier, wrappedEvent);
    }

}
