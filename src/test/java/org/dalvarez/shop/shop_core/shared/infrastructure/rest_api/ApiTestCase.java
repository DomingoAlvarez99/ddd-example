package org.dalvarez.shop.shop_core.shared.infrastructure.rest_api;

import org.dalvarez.shop.shop_common.persistence.domain.repository.GenericRepository;
import org.dalvarez.shop.shop_core.shared.infrastructure.Application;
import org.dalvarez.shop.shop_core.shared.infrastructure.persistence.Seeder;
import org.dalvarez.shop.shop_common.shared.domain.log.Logger;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class ApiTestCase<T, R extends GenericRepository<T, ?>> extends Seeder<T, R> {

    protected ApiTestCase(final R repository,
                          final Logger log) {
        super(repository, log);
    }

}
