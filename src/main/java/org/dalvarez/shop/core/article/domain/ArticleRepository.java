package org.dalvarez.shop.core.article.domain;

import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article, Long> {

    Integer sumStockByCriteria(final Criteria criteria);

    Double sumPriceByCriteria(final Criteria criteria);

}
