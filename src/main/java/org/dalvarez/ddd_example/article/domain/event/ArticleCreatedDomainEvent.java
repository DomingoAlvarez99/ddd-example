package org.dalvarez.ddd_example.article.domain.event;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;

public final class ArticleCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "article.created";

    public ArticleCreatedDomainEvent(final Article value) {
        super(value.id());
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }

}
