package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.DomainEvent;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.log.Logger;
import org.dalvarez.shop.shop_core.shop_common.shared.domain.value_object.id.Identifier;
import org.dalvarez.shop.shop_core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.shop_core.article.application.ArticleRequest;
import org.dalvarez.shop.shop_core.article.domain.ArticleMother;
import org.dalvarez.shop.shop_core.article.domain.event.ArticleCreatedDomainEvent;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public final class ArticleCreatorShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleCreator articleCreator;

    @Autowired
    private Logger log;

    public ArticleCreatorShouldTestCase() {
        articleCreator = new ArticleCreator(repository, categoryRepository, eventBus);
    }

    @Test
    public void createAValidArticle() {
        final Article random = ArticleMother.random();

        final ArticleRequest randomRequest = ArticleRequest.of(random.stock()
                                                                     .value(),
                                                               random.price()
                                                                     .value(),
                                                               random.name()
                                                                     .value(),
                                                               random.description()
                                                                     .value(),
                                                               null
        );

        when(repository.getById(any())).thenReturn(random);

        when(repository.create(any())).thenReturn(random);

        final List<DomainEvent<?>> domainEvents = Collections.singletonList(new ArticleCreatedDomainEvent(random));

        doNothing().when(eventBus)
                   .publish(domainEvents);

        articleCreator.create(randomRequest);

        shouldHaveCreated(random);

        shouldHavePublishedDomainEvents(domainEvents);
    }

}
