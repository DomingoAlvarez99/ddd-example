package org.dalvarez.shop.shop_core.shop_common.persistence.infrastructure.hibernate.criteria;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CriteriaConverter;
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
