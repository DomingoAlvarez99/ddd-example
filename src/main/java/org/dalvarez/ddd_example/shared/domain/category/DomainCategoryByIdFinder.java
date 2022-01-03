package org.dalvarez.ddd_example.shared.domain.category;

import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;

public final class DomainCategoryByIdFinder {

    private final CategoryRepository categoryRepository;

    public DomainCategoryByIdFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category find(final CategoryId categoryId) {
        return categoryRepository.getById(categoryId);
    }

}
