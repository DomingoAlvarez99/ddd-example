package org.dalvarez.shop.core.shared.domain.generator;

import java.util.UUID;

public final class DefaultUuidGenerator implements UuidGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID()
                   .toString();
    }

}
