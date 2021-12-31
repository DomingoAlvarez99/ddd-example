package org.dalvarez.shop.shop_core.shared.application;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.DomainEvent;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.EventBus;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class ApplicationModuleTestCase<T, R extends GenericRepository<T>> {

    protected final R repository;

    protected final CategoryRepository categoryRepository;

    protected final EventBus eventBus;

    public ApplicationModuleTestCase(final Class<R> repositoryClass) {
        repository = mock(repositoryClass);
        categoryRepository = mock(CategoryRepository.class);
        eventBus = mock(EventBus.class);
    }

    protected void shouldHavePublishedDomainEvents(final List<DomainEvent<?>> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
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
