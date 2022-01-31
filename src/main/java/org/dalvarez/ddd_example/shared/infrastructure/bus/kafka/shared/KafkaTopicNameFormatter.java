package org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.shared;

public final class KafkaTopicNameFormatter {

    public static String deadLetter(final String topic) {
        return String.format("dead_letter-%s", topic);
    }

}
