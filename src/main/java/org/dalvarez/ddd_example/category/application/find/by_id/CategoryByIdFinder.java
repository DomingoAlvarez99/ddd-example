package org.dalvarez.ddd_example.category.application.find.by_id;

import org.dalvarez.ddd_example.category.application.CategoryResponse;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.springframework.stereotype.Service;

@Service
public final class CategoryByIdFinder {

    private final CategoryRepository categoryRepository;

    public CategoryByIdFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse find(final String id) {
        return CategoryResponse.fromCategory(categoryRepository.getById(CategoryId.of(id)));
    }

}
