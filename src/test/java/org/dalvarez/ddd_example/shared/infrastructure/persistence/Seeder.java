package org.dalvarez.ddd_example.shared.infrastructure.persistence;

import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.infrastructure.shared.TestConfig;

import java.util.List;

@TestConfig
public abstract class Seeder<T, R extends GenericRepository<T>> {

    private final R repository;

    protected List<T> data;

    protected Seeder(final R repository) {
        this.repository = repository;
    }

    protected void populateDatabase(final List<T> data) {
        repository.deleteByCriteria(new Criteria());

        data.forEach(repository::createOrUpdate);

        this.data = repository.getByCriteria(new Criteria()).result();
    }

}
