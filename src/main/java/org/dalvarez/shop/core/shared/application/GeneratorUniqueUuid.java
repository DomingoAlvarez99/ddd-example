package org.dalvarez.shop.core.shared.application;

import org.dalvarez.shop.shared.persistence.domain.uuid_generator.UuidGenerator;
import org.dalvarez.shop.shared.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shared.shared.infrastructure.validation.Field;
import org.dalvarez.shop.shared.shared.infrastructure.validation.FieldValidator;
import org.dalvarez.shop.shared.shared.infrastructure.validation.UuidValidator;
import org.dalvarez.shop.shared.shared.infrastructure.validation.ValidationNotPassedException;
import org.dalvarez.shop.shared.persistence.infrastructure.shared.exception.NotFoundException;

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
            uuidValidator.validate(new Field<>(uuid));
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
