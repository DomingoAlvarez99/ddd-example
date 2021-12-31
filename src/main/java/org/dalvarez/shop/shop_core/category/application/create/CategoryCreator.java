package org.dalvarez.shop.shop_core.category.application.create;

import org.dalvarez.shop.shop_core.category.application.CategoryRequest;
import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.model.Category;
import org.dalvarez.shop.shop_core.category.domain.model.CategoryName;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;
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
