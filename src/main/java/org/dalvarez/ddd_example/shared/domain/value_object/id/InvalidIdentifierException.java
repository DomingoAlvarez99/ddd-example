package org.dalvarez.ddd_example.shared.domain.value_object.id;

import org.dalvarez.ddd_example.shared.domain.exception.DomainException;

public final class InvalidIdentifierException extends DomainException {

    private static final String ERROR_CODE = "invalid_id";

    private InvalidIdentifierException(String value) {
        super(ERROR_CODE, String.format("The id <%s> is invalid", value));
    }

    public static void throwCauseOf(String value) {
        throw new InvalidIdentifierException(value);
    }

}
