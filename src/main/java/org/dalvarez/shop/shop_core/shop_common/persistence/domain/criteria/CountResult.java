package org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria;

public class CountResult {

    protected final Long total;

    public CountResult() {
        total = 0L;
    }

    public CountResult(final Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

}
