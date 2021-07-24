package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_core.article.application.ArticleResponse;
import org.dalvarez.shop.shop_core.article.domain.Article;
import org.dalvarez.shop.shop_core.article.domain.ArticleCreatedDomainEvent;
import org.dalvarez.shop.shop_core.article.domain.ArticleRepository;
import org.dalvarez.shop.shop_shared.persistence.application.uuid_generator.GeneratorUniqueUuid;
import org.dalvarez.shop.shop_shared.persistence.domain.uuid_generator.UuidGenerator;
import org.dalvarez.shop.shop_shared.event.domain.EventBus;

import java.util.Collections;

public final class ArticleCreator extends GeneratorUniqueUuid {

    private final ArticleRepository articleRepository;

    private final EventBus eventBus;

    public ArticleCreator(final ArticleRepository articleRepository,
                          final UuidGenerator uuidGenerator,
                          final EventBus eventBus) {
        super(articleRepository, uuidGenerator);
        this.articleRepository = articleRepository;
        this.eventBus = eventBus;
    }

    public ArticleResponse create(final Article request) {
        final String uniqueUuid = generate();

        final Article article = articleRepository.create(Article.fromRequest(request, uniqueUuid));

        eventBus.publish(Collections.singletonList(new ArticleCreatedDomainEvent(article)));

        return ArticleResponse.fromArticle(article);
    }

}
