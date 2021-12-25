package org.dalvarez.shop.shop_core.article.application.create;

import org.dalvarez.shop.shop_common.shared.domain.log.Logger;
import org.dalvarez.shop.shop_core.article.domain.ArticleCreatedDomainEvent;
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
                     .getUuid(),
                event.getDate()
        );
    }

}
