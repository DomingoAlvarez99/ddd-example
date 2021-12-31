package org.dalvarez.shop.shop_core.category.application.update;

import org.dalvarez.shop.shop_core.category.application.CategoryRequest;
import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.model.Category;
import org.dalvarez.shop.shop_core.category.domain.model.CategoryName;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;
import org.springframework.stereotype.Service;

@Service
public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    public CategoryUpdater(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse update(final String id,
                                   final CategoryRequest request) {
        final Category category = categoryRepository.getById(CategoryId.of(id));

        final CategoryId parentId = categoryRepository.getById(CategoryId.of(request.parentId()))
                                                      .id();
        category.updateParentId(parentId);

        final CategoryName newName = CategoryName.of(request.name());
        category.rename(newName);

        final Category categoryUpdated = categoryRepository.update(category);

        return CategoryResponse.fromCategory(categoryUpdated);
    }

}
