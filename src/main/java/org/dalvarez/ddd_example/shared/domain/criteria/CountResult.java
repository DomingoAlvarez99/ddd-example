package org.dalvarez.ddd_example.shared.domain.criteria;

public final class CountResult {

    private final Long total;

    private CountResult() {
        total = 0L;
    }

    public CountResult(final Long total) {
        this.total = total;
    }

    public Long total() {
        return total;
    }

}
