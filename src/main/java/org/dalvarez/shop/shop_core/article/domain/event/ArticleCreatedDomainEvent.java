package org.dalvarez.shop.shop_core.article.domain.event;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.bus.DomainEvent;
import org.dalvarez.shop.shop_core.article.domain.model.Article;

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
