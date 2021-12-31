package org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.page;

import java.util.Optional;

public final class Page {

    private static final Long emptyIndex = 0L;

    private static final Long maxSize = (long) Integer.MAX_VALUE;

    private final Long index;

    private final Long size;

    public Page() {
        index = emptyIndex;
        size = maxSize;
    }

    public Page(final Long index,
                final Long size) {
        this.index = Optional.ofNullable(index)
                             .orElse(emptyIndex);
        this.size = Optional.ofNullable(size)
                            .orElse(maxSize);
    }

    public Long getIndex() {
        return index;
    }

    public Long getSize() {
        return size;
    }

    public Boolean hasIndex() {
        return this.index > emptyIndex;
    }

    @Override
    public String toString() {
        return "Page{" +
                "index=" + index +
                ", size=" + size +
                '}';
    }

}
