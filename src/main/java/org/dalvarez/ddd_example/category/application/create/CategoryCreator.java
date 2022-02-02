package org.dalvarez.ddd_example.category.application.create;

import org.dalvarez.ddd_example.category.application.CategoryRequest;
import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.model.CategoryName;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;

public final class CategoryCreator {

    private final CategoryRepository categoryRepository;

    private final DomainCategoryByIdFinder categoryByIdFinder;

    public CategoryCreator(final CategoryRepository categoryRepository,
                           final DomainCategoryByIdFinder categoryByIdFinder) {
        this.categoryRepository = categoryRepository;
        this.categoryByIdFinder = categoryByIdFinder;
    }

    public void create(final CategoryRequest request) {
        CategoryName categoryName = CategoryName.of(request.name());

        final CategoryId parentId = categoryByIdFinder.find(CategoryId.of(request.parentId())).id();

        Category category = Category.create(categoryName, parentId);

        categoryRepository.createOrUpdate(category);
    }

}
