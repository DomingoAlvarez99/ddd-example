package org.dalvarez.ddd_example.article.infrastructure.dependency_injection;

import org.dalvarez.ddd_example.article.application.count.by_criteria.ArticleByCriteriaCounter;
import org.dalvarez.ddd_example.article.application.create.ArticleCreator;
import org.dalvarez.ddd_example.article.application.create.OnArticleCreatedEvent;
import org.dalvarez.ddd_example.article.application.erase.ArticleEraser;
import org.dalvarez.ddd_example.article.application.find.by_criteria.ArticleByCriteriaFinder;
import org.dalvarez.ddd_example.article.application.find.by_id.ArticleByIdFinder;
import org.dalvarez.ddd_example.article.application.sum.by_price.ArticleByPriceAdder;
import org.dalvarez.ddd_example.article.application.sum.by_stock.ArticleByStockAdder;
import org.dalvarez.ddd_example.article.application.update.ArticleUpdater;
import org.dalvarez.ddd_example.article.application.update.OnArticleUpdatedEvent;
import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;
import org.dalvarez.ddd_example.article.infrastructure.persistence.hibernate.repository.HibernateArticleRepository;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
import org.dalvarez.ddd_example.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.shared.domain.transaction_handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ArticleModuleDependencyContainer {

    @Bean
    public ArticleRepository articleRepository(final EntityManager entityManager,
                                               final CriteriaConverter<Article> criteriaConverter) {
        return new HibernateArticleRepository(entityManager, criteriaConverter);
    }

    @Bean
    public ArticleByCriteriaCounter articleByCriteriaCounter(final ArticleRepository articleRepository) {
        return new ArticleByCriteriaCounter(articleRepository);
    }

    @Bean
    public ArticleCreator articleCreator(final ArticleRepository articleRepository,
                                         final DomainCategoryByIdFinder categoryByIdFinder,
                                         final EventBus eventBus,
                                         final TransactionHandler transactionHandler) {
        return new ArticleCreator(
                articleRepository,
                categoryByIdFinder,
                eventBus,
                transactionHandler
        );
    }

    @Bean
    public OnArticleCreatedEvent onArticleCreatedEvent(final Logger log) {
        return new OnArticleCreatedEvent(log);
    }

    @Bean
    public ArticleEraser articleEraser(final ArticleRepository articleRepository) {
        return new ArticleEraser(articleRepository);
    }

    @Bean
    public ArticleByCriteriaFinder articleByCriteriaFinder(final ArticleRepository articleRepository) {
        return new ArticleByCriteriaFinder(articleRepository);
    }

    @Bean
    public ArticleByIdFinder articleByIdFinder(final ArticleRepository articleRepository) {
        return new ArticleByIdFinder(articleRepository);
    }

    @Bean
    public ArticleByPriceAdder articleByPriceAdder(final ArticleRepository articleRepository) {
        return new ArticleByPriceAdder(articleRepository);
    }

    @Bean
    public ArticleByStockAdder articleByStockAdder(final ArticleRepository articleRepository) {
        return new ArticleByStockAdder(articleRepository);
    }

    @Bean
    public ArticleUpdater articleUpdater(final ArticleRepository articleRepository,
                                         final DomainCategoryByIdFinder categoryByIdFinder,
                                         final EventBus eventBus) {
        return new ArticleUpdater(articleRepository, categoryByIdFinder, eventBus);
    }

    @Bean
    public OnArticleUpdatedEvent onArticleUpdatedEvent(final Logger log) {
        return new OnArticleUpdatedEvent(log);
    }

}

