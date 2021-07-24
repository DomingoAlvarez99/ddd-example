package org.dalvarez.shop.shop_core.article.application.find.by_uuid;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;

public final class ArticleByUuidFinder {

    private final ArticleRepository articleRepository;

    public ArticleByUuidFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse find(final String uuid) {
        return ArticleResponse.fromArticle(articleRepository.getByUuid(uuid));
    }

}
