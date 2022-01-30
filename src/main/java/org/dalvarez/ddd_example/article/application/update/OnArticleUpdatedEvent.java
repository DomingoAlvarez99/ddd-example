package org.dalvarez.ddd_example.article.application.update;

import org.dalvarez.ddd_example.article.domain.event.ArticleUpdatedDomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEventSubscriber;
import org.dalvarez.ddd_example.shared.domain.log.Logger;

@DomainEventSubscriber({ArticleUpdatedDomainEvent.class})
public final class OnArticleUpdatedEvent {

    private final Logger log;

    public OnArticleUpdatedEvent(final Logger log) {
        this.log = log;
    }

    @SuppressWarnings("unused")
    public void on(final ArticleUpdatedDomainEvent event) {
        log.info(event.toString());
    }

}