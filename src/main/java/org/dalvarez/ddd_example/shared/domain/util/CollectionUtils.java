package org.dalvarez.ddd_example.shared.domain.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionUtils {

    public static <T extends Collection<?>> Boolean nonEmpty(T collection) {
        return !collection.isEmpty();
    }

    public static <T> Predicate<T> distinctByKey(final Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();

        return t -> seen.add(keyExtractor.apply(t));
    }

    @SafeVarargs
    public static <T> List<T> concat(final List<T>... lists) {
        return Arrays.stream(lists)
                     .flatMap(List::stream)
                     .collect(Collectors.toList());
    }

    public static <T> List<T> concat(final List<T> list1,
                                     final List<T> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                     .collect(Collectors.toList());
    }

}
