package org.dalvarez.shop.core.shared.domain.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public final class CollectionUtils {

    public static <T extends Collection<?>> Boolean nonEmpty(T collection) {
        return !collection.isEmpty();
    }

    public static <T> Predicate<T> distinctByKey(final Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();

        return t -> seen.add(keyExtractor.apply(t));
    }

}
