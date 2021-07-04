package org.dalvarez.shop.core.category.application.create;

import org.dalvarez.shop.core.category.application.CategoryResponse;
import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.category.domain.CategoryRepository;
import org.dalvarez.shop.core.shared.application.GeneratorUniqueUuid;
import org.dalvarez.shop.core.shared.domain.generator.UuidGenerator;
import org.springframework.stereotype.Service;

@Service
public final class CategoryCreator extends GeneratorUniqueUuid {

    private final CategoryRepository categoryRepository;


    public CategoryCreator(final CategoryRepository categoryRepository,
                           final UuidGenerator uuidGenerator) {
        super(categoryRepository, uuidGenerator);
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse create(final Category request) {
        final String uniqueUuid = generate();

        final Category parent = categoryRepository.getByUuid(request.getParent()
                                                                    .getUuid());

        final Category categoryRequest = Category.fromRequest(request, uniqueUuid, parent);

        final Category category = categoryRepository.create(categoryRequest);

        return CategoryResponse.fromCategory(category);
    }

}
