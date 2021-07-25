package org.dalvarez.shop.shop_core.category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.category.domain.CategoryRepository;
import org.dalvarez.shop.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class CategoryRepositoryConfig {

    @Bean
    public CategoryRepository categoryRepository(final EntityManager entityManager,
                                                 final CriteriaConverter<CategoryEntity> hibernateCriteriaConverter) {
        return new HibernateCategoryRepository(entityManager, hibernateCriteriaConverter);
    }

}

