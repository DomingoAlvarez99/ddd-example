package org.dalvarez.ddd_example.shared.domain.criteria;

import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filter;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filters;
import org.dalvarez.ddd_example.shared.domain.criteria.order.Order;
import org.dalvarez.ddd_example.shared.domain.criteria.order.OrderType;
import org.dalvarez.ddd_example.shared.domain.criteria.page.Page;

import java.util.List;

public final class Criteria {

    private Order order;

    private Filters filters;

    private Page page;

    public Criteria() {
        order = new Order(null, OrderType.NONE);
        filters = new Filters();
        page = new Page();
    }

    public static Criteria fromQuery(
            final Class<?> aggregateClass,
            final String orderField,
            final String orderType,
            final String filtersBooleanOperator,
            final String filtersValues,
            final Long pageIndex,
            final Long pageSize) {
        return Criteria.builder()
                       .withOrder(new Order(orderField, OrderType.fromValue(orderType)))
                       .withFilters(Filters.fromQuery(aggregateClass, filtersValues, filtersBooleanOperator))
                       .withPage(new Page(pageIndex, pageSize))
                       .build();
    }

    public Order order() {
        return order;
    }

    public Filters filters() {
        return filters;
    }

    public Page page() {
        return page;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "order=" + order +
                ", filters=" + filters +
                ", page=" + page +
                '}';
    }

    public static class Builder {

        private final Criteria criteria;

        private Builder() {
            criteria = new Criteria();
        }

        public Builder withOrder(final Order order) {
            this.criteria.order = order;

            return this;
        }

        public Builder withPage(final Page page) {
            this.criteria.page = page;

            return this;
        }

        public Builder withFilters(final Filters filters) {
            this.criteria.filters = filters;

            return this;
        }

        public Builder withFilters(final Filter<?>... filters) {
            this.criteria.filters = new Filters(filters);

            return this;
        }

        public Builder withFilter(final Filter<?> filter) {
            this.criteria.filters = new Filters(List.of(filter));

            return this;
        }

        public Criteria build() {
            return criteria;
        }

    }

}
