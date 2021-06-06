package org.dalvarez.shop.core.shared.application;

import org.dalvarez.shop.core.shared.ContextTestCase;
import org.dalvarez.shop.core.shared.domain.bus.event.DomainEvent;
import org.dalvarez.shop.core.shared.domain.bus.event.EventBus;
import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.generator.UuidGenerator;
import org.dalvarez.shop.core.shared.domain.repository.GenericRepository;

import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class ApplicationModuleTestCase<T, R extends GenericRepository<T, Long>> extends ContextTestCase {

    protected final R repository;

    protected final UuidGenerator uuidGenerator;

    protected final EventBus eventBus;

    public ApplicationModuleTestCase(final Class<R> repositoryClass) {
        repository = mock(repositoryClass);
        uuidGenerator = mock(UuidGenerator.class);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHavePublishedDomainEvents(final List<DomainEvent<?>> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    protected void shouldHaveGeneratedAnUuid() {
        verify(uuidGenerator, atLeastOnce()).generate();
    }

    protected void shouldHaveCreated(final T model) {
        verify(repository, atLeastOnce()).create(model);
    }

    protected void shouldHaveErased(final Long id) {
        verify(repository, atLeastOnce()).deleteById(id);
    }

    protected void shouldHaveUpdated(final T model) {
        verify(repository, atLeastOnce()).update(model);
    }

    protected void shouldHaveFoundByCriteria(final Criteria criteria) {
        verify(repository, atLeastOnce()).getByCriteria(criteria);
    }

    protected void shouldHaveFoundById(final Long id) {
        verify(repository, atLeastOnce()).getById(id);
    }

    protected void shouldHaveFoundByUuid(final String uuid) {
        verify(repository, atLeastOnce()).getByUuid(uuid);
    }

}
