package org.dalvarez.shop.shop_core.category.application.find.by_id;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;
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
