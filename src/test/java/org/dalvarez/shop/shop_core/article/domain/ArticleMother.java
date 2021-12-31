package org.dalvarez.shop.shop_core.article.domain;

import org.dalvarez.shop.shop_common.shared.domain.util.CollectionUtils;
import org.dalvarez.shop.shop_core.article.domain.model.Article;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleDescription;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleId;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleName;
import org.dalvarez.shop.shop_core.article.domain.model.ArticlePrice;
import org.dalvarez.shop.shop_core.article.domain.model.ArticleStock;
import org.dalvarez.shop.shop_core.shared.domain.IdMother;
import org.dalvarez.shop.shop_core.shared.domain.category.CategoryId;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ArticleMother {

    public static Article create(final String id,
                                 final Integer stock,
                                 final Double price,
                                 final String name,
                                 final String description) {
        return Article.of(
                ArticleId.of(id),
                ArticleStock.of(stock),
                ArticlePrice.of(price),
                ArticleName.of(name),
                ArticleDescription.of(description),
                null
        );
    }

    public static Article random(final String id) {
        final Random random = new Random();
        final int lowerBound = 1;
        final int upperBound = 100;
        final int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;

        return create(
                id == null ? IdMother.randomPick() : id,
                randomNumber,
                (double) randomNumber,
                "Art " + randomNumber,
                "Desc " + randomNumber
        );
    }

    public static Article random() {
        return random(null);
    }

    public static List<Article> randomList() {
        return IntStream.range(0, 100)
                        .boxed()
                        .map(i -> ArticleMother.random(IdMother.randomGeneration(i)))
                        .filter(CollectionUtils.distinctByKey(Article::name))
                        .filter(CollectionUtils.distinctByKey(Article::id))
                        .collect(Collectors.toList());
    }

}
