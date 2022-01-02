package org.dalvarez.ddd_example.shared.infrastructure.persistence;

import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.infrastructure.shared.TestConfig;

import java.util.List;
import java.util.stream.Collectors;

@TestConfig
public abstract class Seeder<T, R extends GenericRepository<T>> {

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
