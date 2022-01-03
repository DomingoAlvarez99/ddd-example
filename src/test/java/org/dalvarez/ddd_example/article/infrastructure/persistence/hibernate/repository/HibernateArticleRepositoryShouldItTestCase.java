package org.dalvarez.ddd_example.article.infrastructure.persistence.hibernate.repository;

import org.dalvarez.ddd_example.article.domain.ArticleMother;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.model.ArticleName;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.Criteria;
import org.dalvarez.ddd_example.shared.domain.criteria.QueryResult;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filter;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.FilterOperator;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.Filters;
import org.dalvarez.ddd_example.shared.domain.criteria.filter.FiltersBooleanOperator;
import org.dalvarez.ddd_example.shared.domain.criteria.order.Order;
import org.dalvarez.ddd_example.shared.domain.criteria.order.OrderType;
import org.dalvarez.ddd_example.shared.domain.criteria.page.Page;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.shared.infrastructure.persistence.Seeder;
import org.dalvarez.ddd_example.shared.infrastructure.shared.TestConfig;
import org.dalvarez.ddd_example.shared.infrastructure.shared.exception.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
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
        final List<Article> data = ArticleMother.randomList();
        populateDatabase(data);
    }

    @Test
    void shouldCreateTheArticle() {
        final Article newArticle = ArticleMother.random(null);

        final Article expected = articleRepository.create(newArticle);

        final Article actual = articleRepository.getById(expected.id());

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
        final ArticleId idExpected = data.get(1)
                                         .id();

        final Article actual = articleRepository.getById(idExpected);

        assertEquals(idExpected, actual.id());
    }

    @Test
    void shouldNotGetTheArticleById() {
        final ArticleId id = ArticleId.random();

        final NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> articleRepository.getById(id)
        );
        log.info("ActualMessage {}", notFoundException.getMessage());

        final String expectedMessage = NotFoundException.getIdMessage(Article.class, id);

        log.info("ExpectedMessage {}", expectedMessage);

        assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void shouldDeleteTheArticleById() {
        final ArticleId id = data.get(2).id();

        articleRepository.deleteById(id);

        assertThrows(NotFoundException.class, () -> articleRepository.getById(id));
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
    void shouldGetNotGetAnything() {
        final Criteria criteria = Criteria.builder()
                                          .withFilter(new Filter<>(
                                                  Article.FieldNames.NAME,
                                                  FilterOperator.EQUAL,
                                                  ArticleName.of("unknown")
                                          ))
                                          .build();

        final NotFoundException notFoundException = assertThrows(
                NotFoundException.class,
                () -> articleRepository.getByCriteria(criteria)
        );

        log.info("ActualMessage {}", notFoundException.getMessage());

        final String expectedMessage = NotFoundException.getDefaultMessage(Article.class);

        log.info("ExpectedMessage {}", expectedMessage);

        assertEquals(expectedMessage, notFoundException.getMessage());
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
