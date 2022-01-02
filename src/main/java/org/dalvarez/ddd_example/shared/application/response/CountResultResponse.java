package org.dalvarez.ddd_example.shared.application.response;

import com.fasterxml.jackson.annotation.JsonGetter;

public final class CountResultResponse {

    private final Long total;

    private CountResultResponse() {
        total = 0L;
    }

    public CountResultResponse(final Long total) {
        this.total = total;
    }

    @JsonGetter
    public Long total() {
        return total;
    }

}
