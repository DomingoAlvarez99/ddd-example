package org.dalvarez.ddd_example.category.application.update;

import org.dalvarez.ddd_example.category.application.CategoryRequest;
import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.model.CategoryName;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;

public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    private final DomainCategoryByIdFinder categoryByIdFinder;

    public CategoryUpdater(final CategoryRepository categoryRepository,
                           final DomainCategoryByIdFinder categoryByIdFinder) {
        this.categoryRepository = categoryRepository;
        this.categoryByIdFinder = categoryByIdFinder;

    }

    public void update(final String id,
                       final CategoryRequest request) {
        final Category category = categoryByIdFinder.find(CategoryId.of(id));

        final CategoryId parentId = categoryByIdFinder.find(CategoryId.of(request.parentId())).id();
        category.updateParentId(parentId);

        final CategoryName newName = CategoryName.of(request.name());
        category.rename(newName);

        categoryRepository.createOrUpdate(category);
    }

}
