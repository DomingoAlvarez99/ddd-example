package org.dalvarez.shop.core.shared.domain.exception;

public class NonUniqueResultException extends RuntimeException {

    private static final String MESSAGE = "Query returned multiple results";

    public NonUniqueResultException() {
        super(MESSAGE);
    }

}
