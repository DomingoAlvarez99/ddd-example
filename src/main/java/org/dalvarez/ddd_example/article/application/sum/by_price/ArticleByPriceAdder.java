package org.dalvarez.ddd_example.article.application.sum.by_price;

import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.application.response.SumResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;

public final class ArticleByPriceAdder {

    private final ArticleRepository articleRepository;

    public ArticleByPriceAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Double> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumPriceByCriteria(criteria));
    }

}
