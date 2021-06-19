package org.dalvarez.shop.core.article.application.create;

import org.dalvarez.shop.core.article.domain.ArticleCreatedDomainEvent;
import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.springframework.context.event.EventListener;

public final class OnArticleCreatedEvent {

    private final Logger log;

    public OnArticleCreatedEvent(final Logger log) {
        this.log = log;
    }

    @EventListener
    public void on(final ArticleCreatedDomainEvent event) {
        log.info(
                "Event <name={}>, <id={}>, <date={}>",
                event.eventName(),
                event.getValue()
                     .getId(),
                event.getDate()
        );
    }

}
