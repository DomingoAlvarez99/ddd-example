package org.dalvarez.shop.core.article.application.sum.by_stock;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.shared.application.sum.SumResponse;
import org.dalvarez.shop.core.shared.domain.criteria.Criteria;

public final class ArticleByStockAdder {

    private final ArticleRepository articleRepository;

    public ArticleByStockAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Integer> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumStockByCriteria(criteria));
    }

}
