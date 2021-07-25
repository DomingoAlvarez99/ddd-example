package org.dalvarez.shop.shop_core.article_category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.shop_core.article_category.domain.ArticleCategoryRepository;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.CountResult;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_common.persistence.infrastructure.hibernate.HibernateRepository;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

public class HibernateArticleCategoryRepository extends HibernateRepository<ArticleCategoryEntity> implements ArticleCategoryRepository {

    public HibernateArticleCategoryRepository(final EntityManager entityManager,
                                              final CriteriaConverter<ArticleCategoryEntity> hibernateCriteriaConverter) {
        super(entityManager, hibernateCriteriaConverter, ArticleCategoryEntity.class);
    }

    @Override
    public ArticleCategory getById(final Long id) {
        return findById(id).toArticleCategory();
    }

    @Override
    public ArticleCategory getByUuid(final String uuid) {
        return findByUuid(uuid).toArticleCategory();
    }

    @Override
    public QueryResult<ArticleCategory> getByCriteria(final Criteria criteria) {
        final QueryResult<ArticleCategoryEntity> result = findByCriteria(criteria);

        return new QueryResult<>(
                result.getTotalElements(),
                result.getFirstElement(),
                result.getResult()
                      .stream()
                      .map(ArticleCategoryEntity::toArticleCategory)
                      .collect(Collectors.toList())
        );
    }

    @Override
    public CountResult countByCriteria(final Criteria criteria) {
        return super.countByCriteria(criteria);
    }

    @Override
    public ArticleCategory create(final ArticleCategory articleCategory) {
        return save(ArticleCategoryEntity.fromArticleCategory(articleCategory))
                .toArticleCategory();
    }

    @Override
    public ArticleCategory update(final ArticleCategory article) {
        return update(article.getId(), ArticleCategoryEntity.fromArticleCategory(article))
                .toArticleCategory();
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
