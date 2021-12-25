package org.dalvarez.shop.shop_core.category.application.create;

import org.dalvarez.shop.shop_common.persistence.application.uuid_generator.GeneratorUniqueUuid;
import org.dalvarez.shop.shop_common.persistence.domain.uuid_generator.UuidGenerator;
import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
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

        String parentUuid = null;

        if (request.getParentUuid() != null)
            parentUuid = categoryRepository.getByUuid(request.getParentUuid()).getParentUuid();

        final Category categoryRequest = Category.fromRequest(request, uniqueUuid, parentUuid);

        final Category category = categoryRepository.create(categoryRequest);

        return CategoryResponse.fromCategory(category);
    }

}
