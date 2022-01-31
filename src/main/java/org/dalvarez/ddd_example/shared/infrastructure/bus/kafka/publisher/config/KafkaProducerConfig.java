package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.publisher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.publisher.serializer.ObjectMapperSerializer;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared.KafkaConfigProvider;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared.KafkaSharedConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private final ObjectMapper objectMapper;

    private final KafkaConfigProvider configProvider;

    public KafkaProducerConfig(final KafkaConfigProvider configProvider,
                               @Qualifier(KafkaSharedConfig.OBJECT_MAPPER_BEAN_ID) final ObjectMapper objectMapper) {
        this.configProvider = configProvider;
        this.objectMapper = objectMapper;
    }

    @Bean
    public KafkaTemplate<String, ?> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public ProducerFactory<String, ?> producerFactory() {
        final Map<String, Object> configProps = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                configProvider.bootstrapAddress()
        );

        return new DefaultKafkaProducerFactory<>(
                configProps,
                new StringSerializer(),
                new ObjectMapperSerializer(objectMapper)
        );
    }

}
