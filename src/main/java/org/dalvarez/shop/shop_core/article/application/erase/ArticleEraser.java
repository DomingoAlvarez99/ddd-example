package org.dalvarez.shop.shop_core.article.application.erase;

import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;

public class ArticleEraser {

    private final ArticleRepository articleRepository;

    public ArticleEraser(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void erase(final String id) {
        articleRepository.deleteById(ArticleId.of(id));
    }

}
