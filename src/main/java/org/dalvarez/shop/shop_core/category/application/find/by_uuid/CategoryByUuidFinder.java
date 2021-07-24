package org.dalvarez.shop.shop_core.category.application.find.by_uuid;

import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public final class CategoryByUuidFinder {

    private final CategoryRepository categoryRepository;

    public CategoryByUuidFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse find(final String uuid) {
        return CategoryResponse.fromCategory(categoryRepository.getByUuid(uuid));
    }

}
