package org.dalvarez.shop.shop_core.article.application.find.by_id;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;

public final class ArticleByIdFinder {

    private final ArticleRepository articleRepository;

    public ArticleByIdFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse find(final Long id) {
        return ArticleResponse.fromArticle(articleRepository.getById(id));
    }

}
