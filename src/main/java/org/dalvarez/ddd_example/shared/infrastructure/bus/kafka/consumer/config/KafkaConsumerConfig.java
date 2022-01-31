package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared.KafkaConfigProvider;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared.KafkaTopicNameFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private static final long MAX_RETRY_ATTEMPTS = 3L;

    private static final int SECONDS_BETWEEN_RETRY_ATTEMPTS = 15;

    public static final String DEFAULT_CONTAINER_LISTENER_FACTORY_BEAN_ID = "defaultConcurrentKafkaListenerContainerFactory";

    private static final String AUTO_OFFSET_RESET = "earliest";

    private static final String DEFAULT_GROUP_ID = "demo";

    private final KafkaConfigProvider configProvider;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaConsumerConfig(final KafkaConfigProvider configProvider,
                               final KafkaTemplate<String, String> kafkaTemplate) {
        this.configProvider = configProvider;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        final Map<String, Object> props = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, configProvider.bootstrapAddress(),
                ConsumerConfig.GROUP_ID_CONFIG, DEFAULT_GROUP_ID,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET,
                ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false
        );

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new StringDeserializer());
    }

    @Bean(DEFAULT_CONTAINER_LISTENER_FACTORY_BEAN_ID)
    public ConcurrentKafkaListenerContainerFactory<String, String> defaultContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(consumerFactory());
        final DeadLetterPublishingRecoverer deadLetterPublishingRecoverer = new DeadLetterPublishingRecoverer(
                (KafkaOperations<String, String>) kafkaTemplate,
                (record, exception) -> new TopicPartition(
                        KafkaTopicNameFormatter.deadLetter(record.topic()),
                        record.partition()
                )
        );

        final long attemptsInterval = SECONDS_BETWEEN_RETRY_ATTEMPTS * 1000;
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                deadLetterPublishingRecoverer,
                new FixedBackOff(attemptsInterval, MAX_RETRY_ATTEMPTS)
        ));

        return factory;
    }

}
