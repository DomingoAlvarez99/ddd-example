package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;

public abstract class KafkaConsumer {

    private final KafkaListenerEndpointRegistry endpointRegistry;

    private final String listenerContainerId;

    protected KafkaConsumer(final KafkaListenerEndpointRegistry endpointRegistry,
                            final String listenerContainerId) {
        this.endpointRegistry = endpointRegistry;
        this.listenerContainerId = listenerContainerId;
    }

    public void start() {
        container().start();
    }

    public void stop() {
        container().stop();
    }

    public boolean isPaused() {
        return container().isContainerPaused();
    }

    public boolean isRunning() {
        return container().isRunning();
    }

    private MessageListenerContainer container() {
        return endpointRegistry.getListenerContainer(listenerContainerId);
    }

}
