package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.publisher.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public final class ObjectMapperSerializer implements Serializer<Object> {

    private final ObjectMapper objectMapper;

    public ObjectMapperSerializer() {
        this.objectMapper = null;
    }

    public ObjectMapperSerializer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public byte[] serialize(final String topic,
                            final Object data) {
        if (data == null) {
            throw new SerializationException("Null received at serializing");
        }

        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error when serializing " + data + " to byte[]");
        }
    }

}