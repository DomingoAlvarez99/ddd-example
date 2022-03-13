package org.dalvarez.ddd_example.article.infrastructure.persistence.hibernate.repository;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.CountResult;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.value_object.id.Identifier;
import org.dalvarez.ddd_example.shared.infrastructure.persistence.hibernate.HibernateRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class HibernateArticleRepository extends HibernateRepository<Article> implements ArticleRepository {

    public HibernateArticleRepository(final EntityManager entityManager,
                                      final CriteriaConverter<Article> hibernateCriteriaConverter) {
        super(entityManager, hibernateCriteriaConverter, Article.class);
    }

    @Override
    public Optional<Article> getById(final Identifier id) {
        return findById(id);
    }

    @Override
    public QueryResult<Article> getByCriteria(final Criteria criteria) {
        return findByCriteria(criteria);
    }

    @Override
    public CountResult countByCriteria(final Criteria criteria) {
        return super.countByCriteria(criteria);
    }

    @Override
    public void createOrUpdate(final Article article) {
        if (article.id().isNullOrBlank()) {
            save(article);
            return;
        }

        update(article);
    }

    @Override
    public void deleteById(final Identifier id) {
        removeById(id);
    }

    @Override
    public void deleteByCriteria(final Criteria criteria) {
        removeByCriteria(criteria);
    }

    @Override
    public Integer sumStockByCriteria(final Criteria criteria) {
        return sumByCriteria(
                criteria,
                Article.FieldNames.STOCK,
                Integer.class
        );
    }

    @Override
    public Double sumPriceByCriteria(final Criteria criteria) {
        return sumByCriteria(
                criteria,
                Article.FieldNames.PRICE,
                Double.class
        );
    }

}
