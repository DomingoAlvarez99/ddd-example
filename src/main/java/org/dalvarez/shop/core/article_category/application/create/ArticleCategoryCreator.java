package org.dalvarez.shop.core.article_category.application.create;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.core.article_category.domain.ArticleCategoryRepository;
import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.category.domain.CategoryRepository;
import org.dalvarez.shop.shared.persistence.application.GeneratorUniqueUuid;
import org.dalvarez.shop.shared.persistence.domain.uuid_generator.UuidGenerator;
import org.springframework.stereotype.Service;

@Service
public final class ArticleCategoryCreator extends GeneratorUniqueUuid {

    private final ArticleCategoryRepository articleCategoryRepository;

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    public ArticleCategoryCreator(final ArticleCategoryRepository articleCategoryRepository,
                                  final ArticleRepository articleRepository,
                                  final CategoryRepository categoryRepository,
                                  final UuidGenerator uuidGenerator) {
        super(articleCategoryRepository, uuidGenerator);
        this.articleCategoryRepository = articleCategoryRepository;
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArticleCategoryResponse create(final ArticleCategory request) {
        final String uniqueUuid = generate();

        final Article article = articleRepository.getByUuid(request.getArticle()
                                                                   .getUuid());
        final Category category = categoryRepository.getByUuid(request.getCategory()
                                                                      .getUuid());

        final ArticleCategory articleCategory = articleCategoryRepository.create(ArticleCategory.fromRequest(
                uniqueUuid,
                category,
                article
        ));

        return ArticleCategoryResponse.fromArticleCategory(articleCategory);
    }

}
