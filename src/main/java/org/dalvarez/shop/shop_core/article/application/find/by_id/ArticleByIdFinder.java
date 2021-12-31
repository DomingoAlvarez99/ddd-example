package org.dalvarez.shop.shop_core.article.application.find.by_id;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;

public final class ArticleByIdFinder {

    private final ArticleRepository articleRepository;

    public ArticleByIdFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse find(final String id) {
        return ArticleResponse.fromArticle(articleRepository.getById(ArticleId.of(id)));
    }

}
