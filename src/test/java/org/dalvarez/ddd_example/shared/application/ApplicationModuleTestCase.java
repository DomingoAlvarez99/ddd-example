package org.dalvarez.ddd_example.shared.application;

import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;
import org.dalvarez.ddd_example.shared.infrastructure.shared.TestConfig;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class ApplicationModuleTestCase<T, R extends GenericRepository<T>> {

    protected final R repository;

    protected final EventBus eventBus;

    public ApplicationModuleTestCase(final Class<R> repositoryClass) {
        repository = mock(repositoryClass);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHavePublishedDomainEvents(final List<DomainEvent> domainEvents) {
        final List<String> expectedEventNames = domainEvents.stream()
                                                            .map(DomainEvent::eventName)
                                                            .collect(
                                                                    Collectors.toList());

        verify(eventBus, atLeastOnce()).publish(argThat(args -> domainEvents.size() == args.size()));
    }

    protected void shouldHaveCreated(final T model) {
        verify(repository, atLeastOnce()).create(refEq(model, "id"));
    }

    protected void shouldHaveErased(final Identifier id) {
        verify(repository, atLeastOnce()).deleteById(id);
    }

    protected void shouldHaveUpdated(final T model) {
        verify(repository, atLeastOnce()).update(model);
    }

    protected void shouldHaveFoundByCriteria(final Criteria criteria) {
        verify(repository, atLeastOnce()).getByCriteria(criteria);
    }

    protected void shouldHaveFoundById(final Identifier id) {
        verify(repository, atLeastOnce()).getById(id);
    }

}
