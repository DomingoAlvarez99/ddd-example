package org.dalvarez.ddd_example.article.domain.event;

import org.dalvarez.ddd_example.article.domain.model.Article;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;

public final class ArticleCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "article.created";

    private final String name;

    public ArticleCreatedDomainEvent() {
        super();

        this.name = null;
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }


    public ArticleCreatedDomainEvent(final Article value) {
        super(value.id());

        this.name = value.name().value();
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "ArticleCreatedDomainEvent{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }

}
