package org.dalvarez.ddd_example.article.domain.model;


final class InvalidArticleNameException extends InvalidArticleException {

    private static final String BLANK_ERROR_CODE = "blank";

    private static final String EXCEED_MAXIMUM_LENGTH_ERROR_CODE = "exceed_max_length";

    private static final String NOT_REACH_MINIMUM_LENGTH_ERROR_CODE = "not_reach_min_length";

    private InvalidArticleNameException(final String errorCode,
                                        final String message) {
        super(errorCode, message);
    }

    public static void throwCauseOfIsBlank() {
        throwCauseOf(BLANK_ERROR_CODE, "The name is null or empty");
    }


    public static void throwCauseOfExceedMaximumLength(final String value,
                                                       final int length) {
        throwCauseOf(
                EXCEED_MAXIMUM_LENGTH_ERROR_CODE,
                String.format("The name <%s> exceed <%d> chars of length", value, length)
        );
    }

    public static void throwCauseOfNotReachMinimumLength(final String value,
                                                         final int length) {
        throwCauseOf(
                NOT_REACH_MINIMUM_LENGTH_ERROR_CODE,
                String.format("The name <%s> doesn't reach <%d> chars of length", value, length)
        );
    }

    private static void throwCauseOf(final String value,
                                     final String message) {
        throw new InvalidArticleNameException(value, message);
    }

}
