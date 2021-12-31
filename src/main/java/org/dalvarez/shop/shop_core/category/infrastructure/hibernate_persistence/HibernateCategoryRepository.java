package org.dalvarez.shop.shop_core.category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.hibernate.HibernateRepository;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;
import org.dalvarez.shop.shop_core.category.domain.model.Category;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;

import javax.persistence.EntityManager;

public class HibernateCategoryRepository extends HibernateRepository<Category> implements CategoryRepository {

    public HibernateCategoryRepository(final EntityManager entityManager,
                                       final CriteriaConverter<Category> hibernateCriteriaConverter) {
        super(entityManager, hibernateCriteriaConverter, Category.class);
    }

    @Override
    public Category getById(final Identifier id) {
        return findById(id);
    }

    @Override
    public QueryResult<Category> getByCriteria(final Criteria criteria) {
        return findByCriteria(criteria);
    }

    @Override
    public CountResult countByCriteria(final Criteria criteria) {
        return super.countByCriteria(criteria);
    }

    @Override
    public Category create(final Category category) {
        return save(category);
    }

    @Override
    public Category update(final Category category) {
        return super.update(category);
    }

    @Override
    public void deleteById(final Identifier id) {
        removeById(id);
    }

    @Override
    public void deleteByCriteria(final Criteria criteria) {
        removeByCriteria(criteria);
    }


}
