package org.dalvarez.shop.shop_core.article_category.application.update;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategoryRepository;
import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryUpdater {

    private final ArticleCategoryRepository articleCategoryRepository;

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    public ArticleCategoryUpdater(final ArticleCategoryRepository articleCategoryRepository,
                                  final ArticleRepository articleRepository,
                                  final CategoryRepository categoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArticleCategoryResponse update(final ArticleCategory request) {

        final Article article = articleRepository.getByUuid(request.getArticle()
                                                                   .getUuid());
        final Category category = categoryRepository.getByUuid(request.getCategory()
                                                                      .getUuid());

        final ArticleCategory articleCategory = articleCategoryRepository.create(ArticleCategory.fromRequest(
                request.getUuid(),
                category,
                article
        ));

        return ArticleCategoryResponse.fromArticleCategory(articleCategoryRepository.update(articleCategory));
    }

}
