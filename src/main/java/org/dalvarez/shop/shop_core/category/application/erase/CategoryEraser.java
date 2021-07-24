package org.dalvarez.shop.shop_core.category.application.erase;

import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryEraser {

    private final CategoryRepository categoryRepository;

    public CategoryEraser(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void erase(final Long id) {
        categoryRepository.deleteById(id);
    }

}
