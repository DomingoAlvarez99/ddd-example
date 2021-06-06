package org.dalvarez.shop.core.shared.infrastructure;

import org.dalvarez.shop.core.shared.ContextTestCase;
import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.dalvarez.shop.core.shared.domain.repository.GenericRepository;

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
