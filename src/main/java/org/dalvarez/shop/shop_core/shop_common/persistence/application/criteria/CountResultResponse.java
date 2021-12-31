package org.dalvarez.shop.shop_core.shop_common.persistence.application.criteria;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CountResult;

public final class CountResultResponse extends CountResult {

    public CountResultResponse() {

    }

    public CountResultResponse(final Long total) {
        super(total);
    }

}
