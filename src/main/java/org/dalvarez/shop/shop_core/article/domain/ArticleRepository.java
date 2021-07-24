package org.dalvarez.shop.shop_core.article.domain;

import org.dalvarez.shop.shop_shared.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shop_shared.persistence.domain.criteria.Criteria;

public interface ArticleRepository extends GenericRepository<Article, Long> {

    Integer sumStockByCriteria(final Criteria criteria);

    Double sumPriceByCriteria(final Criteria criteria);

}
