package org.dalvarez.shop.core.article_category.infraestructure.hibernate_persistence;

import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleMother;
import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.article_category.domain.ArticleCategory;
import org.dalvarez.shop.core.article_category.domain.ArticleCategoryRepository;
import org.dalvarez.shop.core.article_category.infrastructure.hibernate_persistence.ArticleCategoryEntity;
import org.dalvarez.shop.core.category.domain.Category;
import org.dalvarez.shop.core.category.domain.CategoryRepository;
import org.dalvarez.shop.core.shared.domain.criteria.Criteria;
import org.dalvarez.shop.core.shared.domain.criteria.filter.Filter;
import org.dalvarez.shop.core.shared.domain.criteria.filter.FilterOperator;
import org.dalvarez.shop.core.shared.domain.criteria.page.Page;
import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.dalvarez.shop.core.shared.domain.query_result.QueryResult;
import org.dalvarez.shop.core.shared.infrastructure.Seeder;
import org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence.BaseEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class HibernateArticleCategoryRepositoryShouldItTestCase extends Seeder<ArticleCategory, ArticleCategoryRepository> {

    private final ArticleCategoryRepository articleCategoryRepository;

    private final CategoryRepository categoryRepository;

    private final ArticleRepository articleRepository;

    private final Logger log;

    public HibernateArticleCategoryRepositoryShouldItTestCase(@Autowired ArticleCategoryRepository articleCategoryRepository,
                                                              @Autowired CategoryRepository categoryRepository,
                                                              @Autowired ArticleRepository articleRepository,
                                                              @Autowired Logger log) {
        super(articleCategoryRepository, log);

        this.articleCategoryRepository = articleCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
        this.log = log;
    }

    @BeforeAll
    void setup() {
        Article article = ArticleMother.random();
        articleRepository.create(article);
        Category category = Category.create("kjekw", null, "a");
        categoryRepository.create(category);
        final List<ArticleCategory> data = List.of(
                ArticleCategory.create(
                        "12",
                        articleRepository.getByCriteria(new Criteria())
                                         .getFirstResult(),
                        categoryRepository.getByCriteria(new Criteria())
                                          .getFirstResult()
                )
        );
        populateDatabase(data);
    }

    @Test
    void shouldGetByCriteriaFilteredByANestedObjectProperty() {
        final ArticleCategory expected = data.get(0);

        final Criteria criteria = Criteria.builder()
                                          .withPage(new Page(0L, 1L))
                                          .withFilter(
                                                  new Filter<>(
                                                          ArticleCategoryEntity.FieldNames.CATEGORY + "." + BaseEntity.FieldNames.ID,
                                                          FilterOperator.GREATER_THAN,
                                                          0
                                                  ))
                                          .build();

        final QueryResult<ArticleCategory> queryResult = articleCategoryRepository.getByCriteria(criteria);

        final List<ArticleCategory> actualArticles = queryResult.getResult();

        assertEquals(
                expected.getUuid(),
                actualArticles.get(0)
                              .getUuid()
        );
    }

}
