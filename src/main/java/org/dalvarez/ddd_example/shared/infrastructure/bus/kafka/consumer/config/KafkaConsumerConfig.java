package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared.KafkaConfigProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    public static final String DEFAULT_CONTAINER_LISTENER_FACTORY_BEAN_ID = "defaultConcurrentKafkaListenerContainerFactory";

    private static final String AUTO_OFFSET_RESET = "earliest";

    private static final String DEFAULT_GROUP_ID = "demo";

    private final KafkaConfigProvider configProvider;

    public KafkaConsumerConfig(final KafkaConfigProvider configProvider) {
        this.configProvider = configProvider;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, configProvider.bootstrapAddress(),
                ConsumerConfig.GROUP_ID_CONFIG, DEFAULT_GROUP_ID,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET,
                ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false
        );

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new StringDeserializer());
    }

    @Bean(DEFAULT_CONTAINER_LISTENER_FACTORY_BEAN_ID)
    public ConcurrentKafkaListenerContainerFactory<String, String> defaultContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());

        return factory;
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(1));

        return retryTemplate;
    }

}
