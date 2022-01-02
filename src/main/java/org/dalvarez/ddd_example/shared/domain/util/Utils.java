package org.dalvarez.ddd_example.shared.domain.util;

public final class Utils {

    public static String toSnake(String text) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return text.replaceAll(regex, replacement)
                   .toLowerCase();
    }

}
