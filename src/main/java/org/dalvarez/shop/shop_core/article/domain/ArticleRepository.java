package org.dalvarez.shop.shop_core.article.domain;

import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_common.persistence.domain.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article, String> {

    Integer sumStockByCriteria(final Criteria criteria);

    Double sumPriceByCriteria(final Criteria criteria);

}
