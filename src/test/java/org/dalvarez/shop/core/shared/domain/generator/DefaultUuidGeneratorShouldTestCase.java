package org.dalvarez.shop.core.shared.domain.generator;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class DefaultUuidGeneratorShouldTestCase {

    private static final UuidGenerator defaultUuidGenerator = new DefaultUuidGenerator();

    @Test
    void generateAValidUuid() {
        String expectedUuid = defaultUuidGenerator.generate();

        String actual = UUID.fromString(expectedUuid)
                            .toString();

        assertEquals(expectedUuid, actual);
    }

}
