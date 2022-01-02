package org.dalvarez.ddd_example.article.application.erase;

import org.dalvarez.ddd_example.article.domain.model.ArticleId;
import org.dalvarez.ddd_example.article.domain.repository.ArticleRepository;

public class ArticleEraser {

    private final ArticleRepository articleRepository;

    public ArticleEraser(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void erase(final String id) {
        articleRepository.deleteById(ArticleId.of(id));
    }

}
