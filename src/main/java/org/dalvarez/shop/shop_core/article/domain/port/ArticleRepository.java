package org.dalvarez.shop.shop_core.article.domain.port;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_core.shop_common.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shop_core.article.domain.model.Article;

public interface ArticleRepository extends GenericRepository<Article> {

    Integer sumStockByCriteria(final Criteria criteria);

    Double sumPriceByCriteria(final Criteria criteria);

}
