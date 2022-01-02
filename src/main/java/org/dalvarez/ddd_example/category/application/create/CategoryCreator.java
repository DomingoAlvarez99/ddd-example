package org.dalvarez.ddd_example.category.application.create;

import org.dalvarez.ddd_example.category.application.CategoryRequest;
import org.dalvarez.ddd_example.category.application.CategoryResponse;
import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.model.CategoryName;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.springframework.stereotype.Service;

@Service
public final class CategoryCreator {

    private final CategoryRepository categoryRepository;


    public CategoryCreator(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse create(final CategoryRequest request) {
        CategoryName categoryName = CategoryName.of(request.name());

        final CategoryId parentId = categoryRepository.getById(CategoryId.of(request.parentId()))
                                                      .id();

        Category category = Category.create(categoryName, parentId);

        final Category categoryCreated = categoryRepository.create(category);

        return CategoryResponse.fromCategory(categoryCreated);
    }

}
