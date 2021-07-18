package org.dalvarez.shop.core.shared.infrastructure;

import org.dalvarez.shop.core.shared.ContextTestCase;
import org.dalvarez.shop.shared.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shared.log.domain.Logger;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Seeder<T, R extends GenericRepository<T, ?>> extends ContextTestCase {

    private final Logger log;

    private final R repository;

    protected List<T> data;

    protected Seeder(final R repository,
                     final Logger log) {
        this.log = log;
        this.repository = repository;
    }

    protected void populateDatabase(final List<T> data) {
        repository.deleteByCriteria(new Criteria());

        this.data = data.stream()
                        .map(repository::create)
                        .collect(Collectors.toList());
    }

}
