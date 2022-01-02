package org.dalvarez.ddd_example.shared.domain.criteria;

public class NonUniqueResultException extends RuntimeException {

    private static final String MESSAGE = "Query returned multiple results";

    public NonUniqueResultException() {
        super(MESSAGE);
    }

}
