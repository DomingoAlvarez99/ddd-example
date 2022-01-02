package org.dalvarez.ddd_example.article.domain.repository;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;

public interface ArticleRepository extends GenericRepository<Article> {

    Integer sumStockByCriteria(final Criteria criteria);

    Double sumPriceByCriteria(final Criteria criteria);

}
