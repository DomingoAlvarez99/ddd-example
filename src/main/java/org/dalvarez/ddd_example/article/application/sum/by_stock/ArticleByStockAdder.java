package org.dalvarez.ddd_example.article.application.sum.by_stock;

import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.application.response.SumResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;

public final class ArticleByStockAdder {

    private final ArticleRepository articleRepository;

    public ArticleByStockAdder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public SumResponse<Integer> sum(final Criteria criteria) {
        return new SumResponse<>(articleRepository.sumStockByCriteria(criteria));
    }

}
