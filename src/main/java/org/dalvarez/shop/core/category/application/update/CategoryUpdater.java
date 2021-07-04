package org.dalvarez.shop.core.category.application.update;

import org.dalvarez.shop.core.category.application.CategoryResponse;
import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    public CategoryUpdater(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse update(final Category request) {
        final Category parent = categoryRepository.getByUuid(request.getParent()
                                                                    .getUuid());

        final Category categoryRequest = Category.fromRequest(request, parent);

        final Category category = categoryRepository.create(categoryRequest);

        return CategoryResponse.fromCategory(categoryRepository.update(category));
    }

}
