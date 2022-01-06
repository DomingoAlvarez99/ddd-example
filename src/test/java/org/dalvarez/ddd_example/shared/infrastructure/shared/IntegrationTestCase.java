package org.dalvarez.ddd_example.shared.infrastructure.shared;

import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.infrastructure.Application;
import org.dalvarez.ddd_example.shared.infrastructure.persistence.Seeder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:test.properties")
public class IntegrationTestCase<T, R extends GenericRepository<T>> extends Seeder<T, R> {

    protected IntegrationTestCase(final R repository) {
        super(repository);
    }

}
