package org.dalvarez.shop.shop_core.article_category.application.erase;

import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryEraser {

    private final ArticleCategoryRepository articleCategoryRepository;

    public ArticleCategoryEraser(final ArticleCategoryRepository articleCategoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
    }

    public void erase(final String uuid) {
        articleCategoryRepository.deleteByUuid(uuid);
    }

}