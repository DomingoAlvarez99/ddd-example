package org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria;

public class NonUniqueResultException extends RuntimeException {

    private static final String MESSAGE = "Query returned multiple results";

    public NonUniqueResultException() {
        super(MESSAGE);
    }

}
