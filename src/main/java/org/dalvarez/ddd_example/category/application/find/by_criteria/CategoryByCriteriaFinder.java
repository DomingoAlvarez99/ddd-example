package org.dalvarez.ddd_example.category.application.find.by_criteria;

import org.dalvarez.ddd_example.category.application.CategoryQueryResultResponse;
import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.springframework.stereotype.Service;

@Service
public final class CategoryByCriteriaFinder {

    private final CategoryRepository categoryRepository;

    public CategoryByCriteriaFinder(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryQueryResultResponse find(final Criteria criteria) {
        final QueryResult<Category> queryResult = categoryRepository.getByCriteria(criteria);

        return CategoryQueryResultResponse.of(queryResult);
    }

}
