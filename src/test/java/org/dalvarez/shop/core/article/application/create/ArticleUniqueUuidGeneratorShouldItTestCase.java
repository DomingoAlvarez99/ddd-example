package org.dalvarez.shop.core.article.application.create;

import org.dalvarez.shop.core.article.domain.ArticleRepository;
import org.dalvarez.shop.core.shared.ContextTestCase;
import org.dalvarez.shop.core.shared.application.GeneratorUniqueUuid;
import org.dalvarez.shop.shared.persistence.domain.uuid_generator.UuidGenerator;
import org.dalvarez.shop.shared.log.domain.Logger;
import org.dalvarez.shop.shared.persistence.infrastructure.shared.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ArticleUniqueUuidGeneratorShouldItTestCase extends ContextTestCase {

    private GeneratorUniqueUuid articleGeneratorUniqueUuid;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UuidGenerator uuidGenerator;

    @Autowired
    private Logger log;

    @PostConstruct
    void setup() {
        articleGeneratorUniqueUuid = new GeneratorUniqueUuid(articleRepository, uuidGenerator);
    }

    @Test
    void shouldGenerateAnUniqueUuid() {
        final String uniqueUuid = articleGeneratorUniqueUuid.generate();

        log.info("Unique uuid {}", uniqueUuid);

        assertThrows(NotFoundException.class, () -> articleRepository.getByUuid(uniqueUuid));

        assertNotNull(uniqueUuid);
    }

}
