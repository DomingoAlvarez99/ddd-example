package org.dalvarez.ddd_example.article.application.count.by_criteria;

import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.application.response.CountResultResponse;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;

public final class ArticleByCriteriaCounter {

    private final ArticleRepository articleRepository;

    public ArticleByCriteriaCounter(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public CountResultResponse count(final Criteria criteria) {
        return new CountResultResponse(articleRepository.countByCriteria(criteria).total());
    }

}
