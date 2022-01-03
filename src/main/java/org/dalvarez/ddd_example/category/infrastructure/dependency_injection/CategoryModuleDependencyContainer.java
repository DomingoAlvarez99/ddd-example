package org.dalvarez.ddd_example.category.infrastructure.dependency_injection;

import org.dalvarez.ddd_example.category.application.create.CategoryCreator;
import org.dalvarez.ddd_example.category.application.erase.CategoryEraser;
import org.dalvarez.ddd_example.category.application.find.by_criteria.CategoryByCriteriaFinder;
import org.dalvarez.ddd_example.category.application.find.by_id.CategoryByIdFinder;
import org.dalvarez.ddd_example.category.application.update.CategoryUpdater;
import org.dalvarez.ddd_example.category.domain.model.Category;
import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.category.infrastructure.persistence.hibernate.repository.HibernateCategoryRepository;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
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
    public CategoryCreator categoryCreator(final CategoryRepository categoryRepository,
                                           final DomainCategoryByIdFinder categoryByIdFinder) {
        return new CategoryCreator(categoryRepository, categoryByIdFinder);
    }

    @Bean
    public CategoryEraser categoryEraser(final CategoryRepository categoryRepository) {
        return new CategoryEraser(categoryRepository);
    }

    @Bean
    public CategoryByCriteriaFinder categoryByCriteriaFinder(final CategoryRepository categoryRepository) {
        return new CategoryByCriteriaFinder(categoryRepository);
    }

    @Bean
    public CategoryByIdFinder categoryByIdFinder(final DomainCategoryByIdFinder domainCategoryByIdFinder) {
        return new CategoryByIdFinder(domainCategoryByIdFinder);
    }

    @Bean
    public CategoryUpdater categoryUpdater(final CategoryRepository categoryRepository,
                                           final DomainCategoryByIdFinder categoryByIdFinder) {
        return new CategoryUpdater(categoryRepository, categoryByIdFinder);
    }

}

