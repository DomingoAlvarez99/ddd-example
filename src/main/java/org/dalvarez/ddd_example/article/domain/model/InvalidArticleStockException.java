package org.dalvarez.ddd_example.article.domain.model;


final class InvalidArticleStockException extends InvalidArticleException {

    private static final String NULL_ERROR_CODE = "null";

    private static final String OUT_OF_RANGE_ERROR_CODE = "out_of_range";

    private InvalidArticleStockException(final String errorCode,
                                         final String message) {
        super(errorCode, message);
    }

    public static void throwCauseOfIsNull() {
        throwCauseOf(NULL_ERROR_CODE, "The stock is null");
    }


    public static void throwCauseOfIsNotBetweenExpectedValues(final int value,
                                                              final int minValue,
                                                              final int maxValue) {
        throwCauseOf(
                OUT_OF_RANGE_ERROR_CODE,
                String.format(
                        "The stock <%s> is lower than or equal to <%d> or bigger than or equal to <%d>",
                        value,
                        minValue,
                        maxValue
                )
        );
    }

    private static void throwCauseOf(final String value,
                                     final String message) {
        throw new InvalidArticleStockException(value, message);
    }

}
