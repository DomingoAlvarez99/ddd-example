package org.dalvarez.ddd_example.category.application.erase;

import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.CategoryId;
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
