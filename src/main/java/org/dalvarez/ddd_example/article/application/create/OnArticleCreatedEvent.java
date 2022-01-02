package org.dalvarez.ddd_example.article.application.create;

import org.dalvarez.ddd_example.article.domain.event.ArticleCreatedDomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEventSubscriber;
import org.dalvarez.ddd_example.shared.domain.log.Logger;

@DomainEventSubscriber({ArticleCreatedDomainEvent.class})
public final class OnArticleCreatedEvent {

    private final Logger log;

    public OnArticleCreatedEvent(final Logger log) {
        this.log = log;
    }

    public void on(final ArticleCreatedDomainEvent event) {
        log.info(
                "Event <name={}>, <id={}>, <aggregateId={}>, <date={}>",
                event.eventName(),
                event.id(),
                event.aggregateId(),
                event.date()
        );
    }

}