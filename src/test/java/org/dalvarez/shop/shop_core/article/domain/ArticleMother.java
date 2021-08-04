package org.dalvarez.shop.shop_core.article.domain;

import org.dalvarez.shop.shop_core.shared.domain.UuidMother;
import org.dalvarez.shop.shop_core.shared.domain.util.RandomElementPicker;
import org.dalvarez.shop.shop_common.shared.domain.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ArticleMother {

    public static Article create(final Long id,
                                 final String uuid,
                                 final Integer stock,
                                 final Double price,
                                 final String name,
                                 final String description) {
        return Article.of(
                id,
                uuid,
                stock,
                price,
                name,
                description
        );
    }

    public static Article random(final Long id,
                                 final String uuid) {
        final Random random = new Random();
        final int lowerBound = 1;
        final int upperBound = 100;
        final int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        return create(
                id,
                uuid == null ? UuidMother.randomPick() : uuid,
                randomNumber,
                (double) randomNumber,
                "Art " + randomNumber,
                "Desc " + randomNumber
        );
    }

    public static Article random() {
        return random(randomId(), null);
    }

    public static Long randomId() {
        final Random random = new Random();
        final int lowerBound = 1;
        final int upperBound = 100000;
        final int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        return (long) randomNumber;
    }

    public static Long randomId(List<Article> articles) {
        return RandomElementPicker.from(articles)
                                  .getId();
    }

    public static List<Article> randomList() {
        return randomList(randomId());
    }

    public static List<Article> randomListNullId() {
        return randomList(null);
    }

    private static List<Article> randomList(final Long id) {
        return IntStream.range(0, 100)
                        .boxed()
                        .map(i -> ArticleMother.random(id, UuidMother.randomGeneration(i)))
                        .filter(CollectionUtils.distinctByKey(Article::getName))
                        .filter(CollectionUtils.distinctByKey(Article::getUuid))
                        .collect(Collectors.toList());
    }

}
