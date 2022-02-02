package org.dalvarez.ddd_example.article.infrastructure.persistence.hibernate.repository;

import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.model.ArticleName;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.ArticleInfrastructureModuleTestCase;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filter;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.FilterOperator;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filters;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.FiltersBooleanOperator;
import org.dalvarez.ddd_example.shared.domain.criteria.order.Order;
import org.dalvarez.ddd_example.shared.domain.criteria.order.OrderType;
import org.dalvarez.ddd_example.shared.domain.criteria.page.Page;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final class HibernateArticleRepositoryShouldItTestCase extends ArticleInfrastructureModuleTestCase<Article, ArticleRepository> {

    private final ArticleRepository articleRepository;

    HibernateArticleRepositoryShouldItTestCase(@Autowired ArticleRepository articleRepository) {
        super(articleRepository);

        this.articleRepository = articleRepository;
    }

    @BeforeAll
    void setup() {
        final List<Article> data = ArticleMother.randomList();
        populateDatabase(data);
    }

    @Test
    void shouldCreateTheArticle() {
        final Article newArticle = ArticleMother.random(null);

        articleRepository.createOrUpdate(newArticle);

        final Article articleCreated = articleRepository.getById(newArticle.id()).orElse(null);

        assertEquals(newArticle, articleCreated);
    }

    @Test
    void shouldUpdateTheArticle() {
        final Article articleToUpdate = data.get(0);

        articleRepository.createOrUpdate(articleToUpdate);

        final Article articleUpdated = articleRepository.getById(articleToUpdate.id()).orElse(null);

        assertEquals(articleToUpdate, articleUpdated);
    }

    @Test
    void shouldGetTheArticleById() {
        final ArticleId idExpected = data.get(1)
                                         .id();

        final Article actual = articleRepository.getById(idExpected)
                                                .orElse(null);

        assertNotNull(actual);

        assertEquals(idExpected, actual.id());
    }

    @Test
    void shouldNotGetTheArticleById() {
        final ArticleId id = ArticleId.random();

        final boolean expected = true;
        final boolean actual = articleRepository.getById(id).isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteTheArticleById() {
        final ArticleId id = data.get(2).id();

        articleRepository.deleteById(id);

        final boolean expected = true;
        final boolean actual = articleRepository.getById(id).isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetAll() {
        final Criteria criteria = new Criteria();

        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        final int actualSize = queryResult.result().size();

        final int deletesExpected = 1;

        final int minExpectedSize = data.size() - deletesExpected;

        assertTrue(actualSize >= minExpectedSize);
    }

    @Test
    void shouldNotGetAnything() {
        final Criteria criteria = Criteria.builder()
                                          .withFilter(new Filter<>(
                                                  Article.FieldNames.NAME,
                                                  FilterOperator.EQUAL,
                                                  ArticleName.of("unknown")
                                          ))
                                          .build();

        final boolean expected = true;
        final boolean actual = articleRepository.getByCriteria(criteria).result().isEmpty();

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetByCriteriaOrderedByNameDesc() {
        final Article expected = data.get(3);

        final Criteria criteria = Criteria.builder()
                                          .withPage(new Page(0L, 1L))
                                          .withOrder(new Order(Article.FieldNames.NAME, OrderType.DESC))
                                          .withFilters(
                                                  new Filters(
                                                          FiltersBooleanOperator.AND,
                                                          new Filter<>(
                                                                  Article.FieldNames.NAME,
                                                                  FilterOperator.EQUAL,
                                                                  expected.name()
                                                          ),
                                                          new Filter<>(
                                                                  Article.FieldNames.PRICE,
                                                                  FilterOperator.GREATER_THAN,
                                                                  expected.stock().value() - 1
                                                          ),
                                                          new Filter<>(
                                                                  Article.FieldNames.ID,
                                                                  FilterOperator.EQUAL,
                                                                  expected.id()
                                                          )
                                                  )
                                          )
                                          .build();

        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        final List<Article> actualArticles = queryResult.result();

        assertEquals(expected.name(), actualArticles.get(0).name());
    }

}
