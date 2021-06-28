package org.dalvarez.shop.core.shared.application;

import org.dalvarez.shop.core.shared.domain.generator.UuidGenerator;
import org.dalvarez.shop.core.shared.domain.repository.GenericRepository;
import org.dalvarez.shop.core.shared.domain.validation.Field;
import org.dalvarez.shop.core.shared.domain.validation.FieldValidator;
import org.dalvarez.shop.core.shared.domain.validation.UuidValidator;
import org.dalvarez.shop.core.shared.domain.validation.ValidationNotPassedException;
import org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence.exception.NotFoundException;

public class GeneratorUniqueUuid {

    private final GenericRepository<?, ?> repository;

    private final UuidGenerator uuidGenerator;

    private final FieldValidator uuidValidator = UuidValidator.getInstance();

    public GeneratorUniqueUuid(final GenericRepository<?, ?> repository,
                               final UuidGenerator uuidGenerator) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
    }

    public String generate() {
        String uuid;

        do uuid = uuidGenerator.generate();

        while (alreadyExists(uuid));

        try {
            uuidValidator.validate(new Field<>(UuidValidator.UUID_FIELD_NAME, uuid));
        } catch (ValidationNotPassedException e) {
            throw new IllegalStateException(e);
        }

        return uuid;
    }

    private boolean alreadyExists(final String uuid) {
        try {
            repository.getByUuid(uuid);

            return true;
        } catch (NotFoundException notFoundException) {
            return false;
        }
    }

}
