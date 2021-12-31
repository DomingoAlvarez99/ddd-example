package org.dalvarez.shop.shop_core.category.application.find.by_criteria;

import org.dalvarez.shop.shop_core.shop_common.persistence.application.criteria.QueryResultResponse;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_core.category.application.CategoryResponse;
import org.dalvarez.shop.shop_core.category.domain.model.Category;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public final class CategoryByCriteriaFinder {

    private final CategoryRepository categoryRepository;

    public CategoryByCriteriaFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public QueryResultResponse<CategoryResponse> find(final Criteria criteria) {
        final QueryResult<Category> queryResult = categoryRepository.getByCriteria(criteria);

        return new QueryResultResponse<>(
                queryResult.getTotalElements(),
                queryResult.getFirstElement(),
                queryResult.getResult()
                           .stream()
                           .map(CategoryResponse::fromCategory)
                           .collect(Collectors.toList())
        );
    }

}
