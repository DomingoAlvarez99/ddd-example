package org.dalvarez.shop.core.article.application.sum.by_stock;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.shared.persistence.application.sum.SumResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;

public final class ArticleByStockAdder {

    private final ArticleRepository articleRepository;

    public ArticleByStockAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Integer> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumStockByCriteria(criteria));
    }

}
