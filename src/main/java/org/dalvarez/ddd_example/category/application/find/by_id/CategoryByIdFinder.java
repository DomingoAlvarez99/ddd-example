package org.dalvarez.ddd_example.category.application.find.by_id;

import org.dalvarez.ddd_example.category.application.CategoryResponse;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;

public final class CategoryByIdFinder {

    private final DomainCategoryByIdFinder domainCategoryByIdFinder;

    public CategoryByIdFinder(final DomainCategoryByIdFinder domainCategoryByIdFinder) {
        this.domainCategoryByIdFinder = domainCategoryByIdFinder;
    }

    public CategoryResponse find(final String id) {
        return CategoryResponse.fromCategory(domainCategoryByIdFinder.find(CategoryId.of(id)));
    }

}
