package org.dalvarez.shop.shop_core.article_category.application.find.by_criteria;

import org.dalvarez.shop.shop_core.article_category.application.ArticleCategoryResponse;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategoryRepository;
import org.dalvarez.shop.shop_shared.persistence.application.criteria.QueryResultResponse;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.QueryResult;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public final class ArticleCategoryByCriteriaFinder {

    private final ArticleCategoryRepository articleCategoryRepository;

    public ArticleCategoryByCriteriaFinder(final ArticleCategoryRepository articleCategoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
    }

    public QueryResultResponse<ArticleCategoryResponse> find(final Criteria criteria) {
        final QueryResult<ArticleCategory> queryResult = articleCategoryRepository.getByCriteria(criteria);

        return new QueryResultResponse<>(
                queryResult.getTotalElements(),
                queryResult.getFirstElement(),
                queryResult.getResult()
                           .stream()
                           .map(ArticleCategoryResponse::fromArticleCategory)
                           .collect(Collectors.toList())
        );
    }

}
