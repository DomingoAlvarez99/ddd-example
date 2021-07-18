package org.dalvarez.shop.core.article.application.create;

import org.dalvarez.shop.core.article.application.ArticleApplicationModuleTestCase;
import org.dalvarez.shop.core.article.domain.Article;
import org.dalvarez.shop.core.article.domain.ArticleCreatedDomainEvent;
import org.dalvarez.shop.core.article.domain.ArticleMother;
import org.dalvarez.shop.core.shared.domain.UuidMother;
import org.dalvarez.shop.shared.event.domain.DomainEvent;
import org.dalvarez.shop.shared.log.domain.Logger;
import org.dalvarez.shop.shared.persistence.infrastructure.shared.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public final class ArticleCreatorShouldTestCase extends ArticleApplicationModuleTestCase {

    private final ArticleCreator articleCreator;

    @Autowired
    private Logger log;

    public ArticleCreatorShouldTestCase() {
        articleCreator = new ArticleCreator(repository, uuidGenerator, eventBus);
    }

    @Test
    public void createAValidArticle() {
        final String uuid = UuidMother.randomPick();

        when(uuidGenerator.generate()).thenReturn(uuid);

        when(repository.getByUuid(uuid)).thenThrow(new NotFoundException(uuid));

        final Article request = Article.fromRequest(ArticleMother.random(), uuid);

        final Article articleCreated = ArticleMother.random();

        when(repository.create(request)).thenReturn(articleCreated);

        final List<DomainEvent<?>> domainEvents = Collections.singletonList(new ArticleCreatedDomainEvent(articleCreated));

        doNothing().when(eventBus)
                   .publish(domainEvents);

        articleCreator.create(request);

        shouldHaveGeneratedAnUuid();

        shouldHaveCreated(request);

        shouldHavePublishedDomainEvents(domainEvents);
    }

}
