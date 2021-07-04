package org.dalvarez.shop.core.article_category.application.find.by_uuid;

import org.dalvarez.shop.core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.core.article_category.domain.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public final class ArticleCategoryByUuidFinder {

    private final ArticleCategoryRepository categoryRepository;

    public ArticleCategoryByUuidFinder(final ArticleCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ArticleCategoryResponse find(final String uuid) {
        return ArticleCategoryResponse.fromArticleCategory(categoryRepository.getByUuid(uuid));
    }

}

