package org.dalvarez.shop.core.category.application.find.by_id;

import org.dalvarez.shop.core.category.application.CategoryResponse;
import org.dalvarez.shop.core.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public final class CategoryByIdFinder {

    private final CategoryRepository categoryRepository;

    public CategoryByIdFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse find(final Long id) {
        return CategoryResponse.fromCategory(categoryRepository.getById(id));
    }

}
