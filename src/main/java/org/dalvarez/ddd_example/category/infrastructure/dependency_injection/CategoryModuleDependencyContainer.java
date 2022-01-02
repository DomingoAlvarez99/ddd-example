package org.dalvarez.ddd_example.category.infrastructure.dependency_injection;

import org.dalvarez.ddd_example.category.application.create.CategoryCreator;
import org.dalvarez.ddd_example.category.application.erase.CategoryEraser;
import org.dalvarez.ddd_example.category.application.find.by_criteria.CategoryByCriteriaFinder;
import org.dalvarez.ddd_example.category.application.find.by_id.CategoryByIdFinder;
import org.dalvarez.ddd_example.category.application.update.CategoryUpdater;
import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.category.infrastructure.persistence.hibernate.repository.HibernateCategoryRepository;
import org.dalvarez.ddd_example.shared.domain.criteria.CriteriaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class CategoryModuleDependencyContainer {

    @Bean
    public CategoryRepository categoryRepository(final EntityManager entityManager,
                                                 final CriteriaConverter<Category> hibernateCriteriaConverter) {
        return new HibernateCategoryRepository(entityManager, hibernateCriteriaConverter);
    }

    @Bean
    public CategoryCreator CategoryCreator(final CategoryRepository categoryRepository) {
        return new CategoryCreator(categoryRepository);
    }

    @Bean
    public CategoryEraser CategoryEraser(final CategoryRepository categoryRepository) {
        return new CategoryEraser(categoryRepository);
    }

    @Bean
    public CategoryByCriteriaFinder CategoryByCriteriaFinder(final CategoryRepository categoryRepository) {
        return new CategoryByCriteriaFinder(categoryRepository);
    }

    @Bean
    public CategoryByIdFinder CategoryByIdFinder(final CategoryRepository categoryRepository) {
        return new CategoryByIdFinder(categoryRepository);
    }

    @Bean
    public CategoryUpdater CategoryUpdater(final CategoryRepository categoryRepository) {
        return new CategoryUpdater(categoryRepository);
    }

}

