package org.dalvarez.shop.core.shared.infrastructure.hibernate_persistence;

import org.dalvarez.shop.core.shared.domain.criteria.CriteriaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class HibernateCriteriaConverterConfig {

    @Bean
    public CriteriaConverter<?> hibernateCriteriaConverter(final EntityManager entityManager) {
        return new HibernateCriteriaConverter<>(entityManager.getCriteriaBuilder());
    }

}
