package org.dalvarez.ddd_example.shared.domain.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomElementPicker {

    public static <T> T from(final List<T> elements) {
        final Random rand = new Random();

        return elements.get(rand.nextInt(elements.size()));
    }

    @SafeVarargs
    public static <T> T from(final T... elements) {
        return from(Arrays.asList(elements));
    }

}
