package org.dalvarez.shop.core.shared.domain.generator;

import org.dalvarez.shop.core.shared.ContextTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

final class UuidGeneratorConfigurationTestCase extends ContextTestCase {

    @Autowired
    private UuidGenerator uuidGenerator;

    @Test
    void shouldGenerateAnUuid() {
        assertNotNull(uuidGenerator.generate());
    }

}
