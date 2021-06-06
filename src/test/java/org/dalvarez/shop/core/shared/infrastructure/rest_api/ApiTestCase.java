package org.dalvarez.shop.core.shared.infrastructure.rest_api;

import org.dalvarez.shop.core.shared.domain.log.Logger;
import org.dalvarez.shop.core.shared.domain.repository.GenericRepository;
import org.dalvarez.shop.core.shared.infrastructure.Seeder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTestCase<T, R extends GenericRepository<T, ?>> extends Seeder<T, R> {

    protected ApiTestCase(final R repository,
                          final Logger log) {
        super(repository, log);
    }

}
