package org.dalvarez.ddd_example.article.infrastructure;

import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.infrastructure.shared.IntegrationTestCase;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleInfrastructureModuleTestCase<T, R extends GenericRepository<T>> extends IntegrationTestCase<T, R> {

    protected ArticleInfrastructureModuleTestCase(final R repository) {
        super(repository);
    }

}
