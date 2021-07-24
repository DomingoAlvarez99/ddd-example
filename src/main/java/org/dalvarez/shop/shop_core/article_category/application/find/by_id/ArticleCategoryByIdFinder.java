package org.dalvarez.shop.shop_core.article_category.application.find.by_id;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public final class ArticleCategoryByIdFinder {

    private final ArticleCategoryRepository articleCategoryRepository;

    public ArticleCategoryByIdFinder(final ArticleCategoryRepository articleCategoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
    }

    public ArticleCategoryResponse find(final Long id) {
        return ArticleCategoryResponse.fromArticleCategory(articleCategoryRepository.getById(id));
    }

}
