package org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.hibernate.HibernateRepository;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.port.ArticleRepository;

import javax.persistence.EntityManager;

public class HibernateArticleRepository extends HibernateRepository<Article> implements ArticleRepository {

    public HibernateArticleRepository(final EntityManager entityManager,
                                      final CriteriaConverter<Article> hibernateCriteriaConverter) {
        super(entityManager, hibernateCriteriaConverter, Article.class);
    }

    @Override
    public Article getById(final Identifier id) {
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
    public Article create(final Article article) {
        return save(article);
    }

    @Override
    public Article update(final Article article) {
        return super.update(article);
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
