package org.dalvarez.shop.shop_core.article.application.count.by_criteria;

import org.dalvarez.shop.shop_core.shop_common.persistence.application.criteria.CountResultResponse;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;

public final class ArticleByCriteriaCounter {

    private final ArticleRepository articleRepository;

    public ArticleByCriteriaCounter(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public CountResultResponse count(final Criteria criteria) {
        return new CountResultResponse(articleRepository.countByCriteria(criteria)
                                                        .getTotal());
    }

}
