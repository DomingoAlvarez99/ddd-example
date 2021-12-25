package org.dalvarez.shop.shop_core.shared.application;

import org.dalvarez.shop.shop_common.persistence.domain.uuid_generator.UuidGenerator;
import org.dalvarez.shop.shop_common.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shop_common.shared.domain.bus.DomainEvent;
import org.dalvarez.shop.shop_common.shared.domain.bus.EventBus;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;

import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class ApplicationModuleTestCase<T, R extends GenericRepository<T, String>> {

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

    protected void shouldHaveErased(final String uuid) {
        verify(repository, atLeastOnce()).deleteByUuid(uuid);
    }

    protected void shouldHaveUpdated(final T model) {
        verify(repository, atLeastOnce()).update(model);
    }

    protected void shouldHaveFoundByCriteria(final Criteria criteria) {
        verify(repository, atLeastOnce()).getByCriteria(criteria);
    }

    protected void shouldHaveFoundByUuid(final String uuid) {
        verify(repository, atLeastOnce()).getByUuid(uuid);
    }

}
