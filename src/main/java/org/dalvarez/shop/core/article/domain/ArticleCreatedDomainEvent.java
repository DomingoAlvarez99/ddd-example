package org.dalvarez.shop.core.article.domain;

import org.dalvarez.shop.shared.event.domain.DomainEvent;

public final class ArticleCreatedDomainEvent extends DomainEvent<Article> {

    private static final String EVENT_NAME = "article.created";

    public ArticleCreatedDomainEvent(final Article value) {
        super(value);
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

}
