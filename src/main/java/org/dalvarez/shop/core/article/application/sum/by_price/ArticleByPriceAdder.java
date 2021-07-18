package org.dalvarez.shop.core.article.application.sum.by_price;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.shared.persistence.application.sum.SumResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;

public final class ArticleByPriceAdder {

    private final ArticleRepository articleRepository;

    public ArticleByPriceAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Double> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumPriceByCriteria(criteria));
    }

}
