package org.dalvarez.shop.core.shared.domain.util;

import java.util.Objects;

public final class StringUtils {

    public static final String EMPTY = "";

    public static final String SPACE = " ";

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String PATH_DELIMITER = "/";

    private static final String ESCAPE = "\\";

    public static final String STRING_FORMAT = "%s";

    public static final String INTEGER_FORMAT = "%d";

    private StringUtils() {
        throw new IllegalStateException();
    }

    public static String removeAllWhiteSpaces(final String str) {
        return replaceWhiteSpaces(str, EMPTY);
    }

    public static String replaceWhiteSpacesWithSpaces(final String str) {
        return replaceWhiteSpaces(str, SPACE);
    }

    public static String replaceWhiteSpacesWith(final String str,
                                                final String with) {
        return replaceWhiteSpaces(str, with);
    }

    private static String replaceWhiteSpaces(final String str,
                                             final String charToReplace) {
        if (Objects.isNull(str)) {
            return null;
        }

        return str.replaceAll(whiteSpacesRe(), charToReplace);
    }

    private static boolean isNullOrBlank(final String str) {
        return Objects.isNull(str) || str.isBlank();
    }

    public static String escapePattern(String str) {
        if (isNullOrBlank(str)) {
            return str;
        }

        return ESCAPE + str;
    }

    public static String tabRe() {
        final String tab = "t";

        return escapePattern(tab);
    }

    public static boolean nonEmpty(String str) {
        return !Objects.isNull(str) && !str.isBlank();
    }

    public static String whiteSpacesRe() {
        final String whiteSpaces = "s+";

        return escapePattern(whiteSpaces);
    }

}
