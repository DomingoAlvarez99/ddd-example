package org.dalvarez.shop.shop_core.article.application.sum.by_stock;

import org.dalvarez.shop.shop_common.persistence.application.shared.sum.SumResponse;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;

public final class ArticleByStockAdder {

    private final ArticleRepository articleRepository;

    public ArticleByStockAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Integer> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumStockByCriteria(criteria));
    }

}