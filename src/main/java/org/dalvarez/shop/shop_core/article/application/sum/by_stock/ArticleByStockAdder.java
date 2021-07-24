package org.dalvarez.shop.shop_core.article.application.sum.by_stock;

import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_shared.persistence.application.shared.sum.SumResponse;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.Criteria;

public final class ArticleByStockAdder {

    private final ArticleRepository articleRepository;

    public ArticleByStockAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Integer> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumStockByCriteria(criteria));
    }

}
