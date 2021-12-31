package org.dalvarez.shop.shop_core.category.application.erase;

import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;
import org.springframework.stereotype.Service;

@Service
public class CategoryEraser {

    private final CategoryRepository categoryRepository;

    public CategoryEraser(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void erase(final String id) {
        categoryRepository.deleteById(CategoryId.of(id));
    }

}
