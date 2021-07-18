package org.dalvarez.shop.core.category.application.find.by_criteria;

import org.dalvarez.shop.core.category.application.CategoryResponse;
import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.category.domain.CategoryRepository;
import org.dalvarez.shop.core.shared.application.QueryResultResponse;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shared.persistence.domain.criteria.QueryResult;
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
