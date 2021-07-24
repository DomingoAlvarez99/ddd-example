package org.dalvarez.shop.shop_core.shared.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence.ArticleEntity;
import org.dalvarez.shop.shop_core.shared.ContextTestCase;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.filter.Filter;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.filter.FilterOperator;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.filter.Filters;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.order.Order;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.order.OrderType;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.page.Page;
import org.dalvarez.shop.shop_shared.persistence.infrastructure.hibernate.BaseEntity;
import org.dalvarez.shop.shop_shared.persistence.infrastructure.hibernate.criteria.HibernateCriteriaConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaQuery;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final class HibernateCriteriaConverterShouldItTestCase extends ContextTestCase {

    @Autowired
    private HibernateCriteriaConverter<ArticleEntity> hibernateCriteriaConverter;

    @Test
    void shouldHasDescendingOrder() {
        final Criteria criteria = Criteria.builder()
                                          .withFilters(
                                                  new Filters(
                                                          new Filter<>(
                                                                  BaseEntity.FieldNames.ID,
                                                                  FilterOperator.GREATER_THAN,
                                                                  3L
                                                          ),
                                                          new Filter<>(
                                                                  BaseEntity.FieldNames.ID,
                                                                  FilterOperator.LESS_THAN,
                                                                  5L
                                                          )
                                                  )
                                          )
                                          .withOrder(new Order(BaseEntity.FieldNames.UUID, OrderType.DESC))
                                          .withPage(new Page(2L, 2L))
                                          .build();

        final CriteriaQuery<ArticleEntity> hibernateCriteria = hibernateCriteriaConverter.convert(
                criteria,
                ArticleEntity.class
        );

        final boolean isAscending = hibernateCriteria.getOrderList()
                                                     .get(0)
                                                     .isAscending();

        assertFalse(isAscending);
    }


}
