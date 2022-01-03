package org.dalvarez.ddd_example.article.domain.model;


final class InvalidArticlePriceException extends InvalidArticleException {

    private static final String NULL_ERROR_CODE = "null";

    private static final String OUT_OF_RANGE_ERROR_CODE = "out_of_range";

    private InvalidArticlePriceException(final String errorCode,
                                         final String message) {
        super(errorCode, message);
    }

    public static void throwCauseOfIsNull() {
        throwCauseOf(NULL_ERROR_CODE, "The price is null");
    }


    public static void throwCauseOfIsNotBetweenExpectedValues(final double value,
                                                              final double minValue,
                                                              final double maxValue) {
        throwCauseOf(
                OUT_OF_RANGE_ERROR_CODE,
                String.format("The price <%s> is lower than <%f.2> or bigger than <%f.2>", value, minValue, maxValue)
        );
    }

    private static void throwCauseOf(final String value,
                                     final String message) {
        throw new InvalidArticlePriceException(value, message);
    }

}
