package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.consumer.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ObjectMapperDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper;

    private final Class<T> clazz;

    public ObjectMapperDeserializer() {
        this.objectMapper = null;
        this.clazz = null;
    }

    public ObjectMapperDeserializer(final Class<T> clazz,
                                    final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.clazz = clazz;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public T deserialize(final String topic,
                         final byte[] data) {
        if (data == null) {
            throw new SerializationException("Null received at deserializing");
        }

        try {
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), clazz);
        } catch (IOException e) {
            throw new SerializationException("Error when deserializing to" + clazz);
        }
    }

}
