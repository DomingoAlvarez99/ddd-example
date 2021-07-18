package org.dalvarez.shop.core.article.domain;

import org.dalvarez.shop.shared.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shared.persistence.domain.criteria.Criteria;

public interface ArticleRepository extends GenericRepository<Article, Long> {

    Integer sumStockByCriteria(final Criteria criteria);

    Double sumPriceByCriteria(final Criteria criteria);

}
