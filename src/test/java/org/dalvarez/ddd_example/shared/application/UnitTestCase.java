package org.dalvarez.ddd_example.shared.application;

import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class UnitTestCase<T, R extends GenericRepository<T>> {

    protected final R repository;

    protected final EventBus eventBus;

    protected UnitTestCase(final Class<R> repositoryClass) {
        repository = mock(repositoryClass);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHavePublishedDomainEvents(final List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(argThat(args -> domainEvents.size() == args.size()));
    }

    protected void shouldHaveCreated(final T model) {
        verify(repository, atLeastOnce()).createOrUpdate(refEq(model, "id"));
    }

    protected void shouldHaveErased(final Identifier id) {
        verify(repository, atLeastOnce()).deleteById(id);
    }

    protected void shouldHaveUpdated(final T model) {
        verify(repository, atLeastOnce()).createOrUpdate(model);
    }

    protected void shouldHaveFoundByCriteria(final Criteria criteria) {
        verify(repository, atLeastOnce()).getByCriteria(criteria);
    }

    protected void shouldHaveFoundById(final Identifier id) {
        verify(repository, atLeastOnce()).getById(id);
    }

}
