package org.dalvarez.shop.shop_core.article.application.update;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;

public class ArticleUpdater {

    private final ArticleRepository articleRepository;

    public ArticleUpdater(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse update(final Article article) {
        return ArticleResponse.fromArticle(articleRepository.update(Article.fromRequest(article, article.getUuid())));
    }

}
