package org.dalvarez.ddd_example.category.infrastructure.persistence.hibernate.repository;

import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.CountResult;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;
import org.dalvarez.ddd_example.shared.infrastructure.persistence.hibernate.HibernateRepository;

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
