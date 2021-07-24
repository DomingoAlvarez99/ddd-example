package org.dalvarez.shop.shop_core.category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.category.domain.Category;
import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_shared.persistence.infrastructure.hibernate.HibernateRepository;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

public class HibernateCategoryRepository extends HibernateRepository<CategoryEntity> implements CategoryRepository {

    public HibernateCategoryRepository(final EntityManager entityManager,
                                       final CriteriaConverter<CategoryEntity> hibernateCriteriaConverter) {
        super(entityManager, hibernateCriteriaConverter, CategoryEntity.class);
    }

    @Override
    public Category getById(final Long id) {
        return findById(id).toCategory();
    }

    @Override
    public Category getByUuid(final String uuid) {
        return findByUuid(uuid).toCategory();
    }

    @Override
    public QueryResult<Category> getByCriteria(final Criteria criteria) {
        final QueryResult<CategoryEntity> result = findByCriteria(criteria);

        return new QueryResult<>(
                result.getTotalElements(),
                result.getFirstElement(),
                result.getResult()
                      .stream()
                      .map(CategoryEntity::toCategory)
                      .collect(Collectors.toList())
        );
    }

    @Override
    public CountResult countByCriteria(final Criteria criteria) {
        return super.countByCriteria(criteria);
    }

    @Override
    public Category create(final Category article) {
        return save(CategoryEntity.fromCategory(article))
                .toCategory();
    }

    @Override
    public Category update(final Category article) {
        return update(article.getId(), CategoryEntity.fromCategory(article))
                .toCategory();
    }

    @Override
    public void deleteById(final Long id) {
        removeById(id);
    }

    @Override
    public void deleteByCriteria(final Criteria criteria) {
        removeByCriteria(criteria);
    }


}
