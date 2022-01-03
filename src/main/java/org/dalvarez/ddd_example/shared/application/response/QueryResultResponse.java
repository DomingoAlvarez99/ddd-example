package org.dalvarez.ddd_example.shared.application.response;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class QueryResultResponse<REQ, RES> {

    private final Long totalElements;

    private final Long firstElement;

    private final List<RES> result;

    protected QueryResultResponse() {
        totalElements = null;
        firstElement = null;
        result = null;
    }

    protected QueryResultResponse(final Long totalElements,
                                  final Long firstElement,
                                  final List<REQ> result) {
        this.totalElements = totalElements;
        this.firstElement = firstElement;
        this.result = result.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    protected abstract RES mapToResponse(REQ request);

    @JsonGetter
    public Long totalElements() {
        return totalElements;
    }

    @JsonGetter
    public Long firstElement() {
        return firstElement;
    }

    @JsonGetter
    public List<RES> result() {
        return result;
    }

}
