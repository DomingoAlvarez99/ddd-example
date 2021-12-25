package org.dalvarez.shop.shop_core.category.application.update;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    public CategoryUpdater(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse update(final Category request) {
        final Category parent = categoryRepository.getByUuid(request.getParentUuid());

        final Category categoryRequest = Category.fromRequest(request, parent.getParentUuid());

        final Category category = categoryRepository.create(categoryRequest);

        return CategoryResponse.fromCategory(categoryRepository.update(category));
    }

}
