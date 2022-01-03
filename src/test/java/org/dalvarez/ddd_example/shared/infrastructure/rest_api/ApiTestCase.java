package org.dalvarez.ddd_example.shared.infrastructure.rest_api;

import org.dalvarez.ddd_example.shared.domain.repository.GenericRepository;
import org.dalvarez.ddd_example.shared.infrastructure.Application;
import org.dalvarez.ddd_example.shared.infrastructure.persistence.Seeder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class ApiTestCase<T, R extends GenericRepository<T>> extends Seeder<T, R> {

    protected ApiTestCase(final R repository) {
        super(repository);
    }

}
