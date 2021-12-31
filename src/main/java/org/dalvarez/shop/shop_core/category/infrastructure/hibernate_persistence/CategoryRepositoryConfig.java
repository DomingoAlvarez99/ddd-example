package org.dalvarez.shop.shop_core.category.infrastructure.hibernate_persistence;

import org.dalvarez.shop.shop_core.shop_common.persistence.domain.criteria.CriteriaConverter;
import org.dalvarez.shop.shop_core.category.domain.model.Category;
import org.dalvarez.shop.shop_core.category.domain.port.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class CategoryRepositoryConfig {

    @Bean
    public CategoryRepository categoryRepository(final EntityManager entityManager,
                                                 final CriteriaConverter<Category> hibernateCriteriaConverter) {
        return new HibernateCategoryRepository(entityManager, hibernateCriteriaConverter);
    }

}

