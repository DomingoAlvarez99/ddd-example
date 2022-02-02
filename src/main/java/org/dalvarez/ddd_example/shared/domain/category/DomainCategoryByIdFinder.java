package org.dalvarez.ddd_example.shared.domain.category;

import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.infrastructure.shared.exception.NotFoundException;

public final class DomainCategoryByIdFinder {

    private final CategoryRepository categoryRepository;

    public DomainCategoryByIdFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category find(final CategoryId categoryId) {
        return categoryRepository.getById(categoryId)
                                 .orElseThrow(() -> NotFoundException.build(Category.class, categoryId));
    }

}
