package org.dalvarez.shop.shop_core.article.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_core.shared.infrastructure.Seeder;
import org.dalvarez.shop.shop_common.log.domain.Logger;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.Criteria;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.QueryResult;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.filter.Filter;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.filter.FilterOperator;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.filter.Filters;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.filter.FiltersBooleanOperator;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.order.Order;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.order.OrderType;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.page.Page;
import org.dalvarez.shop.shop_common.persistence.infrastructure.hibernate.BaseEntity;
import org.dalvarez.shop.shop_common.persistence.infrastructure.shared.exception.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class HibernateArticleRepositoryShouldItTestCase extends Seeder<Article, ArticleRepository> {

    private final ArticleRepository articleRepository;

    private final Logger log;

    public HibernateArticleRepositoryShouldItTestCase(@Autowired ArticleRepository articleRepository,
                                                      @Autowired Logger log) {
        super(articleRepository, log);

        this.articleRepository = articleRepository;
        this.log = log;
    }

    @BeforeAll
    void setup() {
        final List<Article> data = ArticleMother.randomListNullId();
        populateDatabase(data);
    }

    @Test
    void shouldCreateTheArticle() {
        final Article newArticle = ArticleMother.random(null, null);

        final Article expected = articleRepository.create(newArticle);

        final Article actual = articleRepository.getByUuid(expected.getUuid());

        log.info(expected.toString());

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateTheArticle() {
        final Article actual = data.get(0);

        log.info("Actual: {}", actual.toString());

        final Article expected = articleRepository.update(actual);

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetTheArticleById() {
        final Long expected = data.get(1)
                                  .getId();

        final Article actual = articleRepository.getById(expected);

        assertEquals(expected, actual.getId());
    }

    @Test
    void shouldNotGetTheArticleById() {
        final Long id = 0L;

        final NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> articleRepository.getById(id)
        );
        log.info("ActualMessage {}", notFoundException.getMessage());

        final String expectedMessage = NotFoundException.getIdMessage(ArticleEntity.class, id);

        log.info("ExpectedMessage {}", expectedMessage);

        assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void shouldDeleteTheArticleById() {
        final Long id = data.get(2)
                            .getId();

        articleRepository.deleteById(id);

        assertThrows(NotFoundException.class, () -> articleRepository.getById(id));
    }

    @Test
    void shouldGetAll() {
        final Criteria criteria = new Criteria();

        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        final int actualSize = queryResult.getResult()
                                          .size();

        final int deletesExpected = 1;

        final int minExpectedSize = data.size() - deletesExpected;

        assertTrue(actualSize >= minExpectedSize);
    }

    @Test
    void shouldGetNotGetAnything() {
        final Criteria criteria = Criteria.builder()
                                          .withFilter(new Filter<>(
                                                  ArticleEntity.FieldNames.NAME,
                                                  FilterOperator.EQUAL,
                                                  "unknown"
                                          ))
                                          .build();

        final NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> articleRepository.getByCriteria(criteria)
        );

        log.info("ActualMessage {}", notFoundException.getMessage());

        final String expectedMessage = NotFoundException.getDefaultMessage(ArticleEntity.class);

        log.info("ExpectedMessage {}", expectedMessage);

        assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void shouldGetByCriteriaOrderedByNameDesc() {
        final Article expected = data.get(3);

        final Criteria criteria = Criteria.builder()
                                          .withPage(new Page(0L, 1L))
                                          .withOrder(new Order(ArticleEntity.FieldNames.NAME, OrderType.DESC))
                                          .withFilters(
                                                  new Filters(
                                                          FiltersBooleanOperator.AND,
                                                          new Filter<>(
                                                                  BaseEntity.FieldNames.ID,
                                                                  FilterOperator.EQUAL,
                                                                  expected.getId()
                                                          ),
                                                          new Filter<>(
                                                                  ArticleEntity.FieldNames.NAME,
                                                                  FilterOperator.EQUAL,
                                                                  expected.getName()
                                                          ),
                                                          new Filter<>(
                                                                  ArticleEntity.FieldNames.PRICE,
                                                                  FilterOperator.GREATER_THAN,
                                                                  expected.getPrice() - 1
                                                          )
                                                  )
                                          )
                                          .build();

        final QueryResult<Article> queryResult = articleRepository.getByCriteria(criteria);

        final List<Article> actualArticles = queryResult.getResult();

        assertEquals(
                expected.getName(),
                actualArticles.get(0)
                              .getName()
        );
    }

}
