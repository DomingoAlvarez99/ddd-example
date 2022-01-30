package org.dalvarez.ddd_example.shared.infrastructure.dependency_injection;

import org.dalvarez.ddd_example.category.domain.repository.CategoryRepository;
import org.dalvarez.ddd_example.shared.domain.bus.DomainEvent;
import org.dalvarez.ddd_example.shared.domain.bus.EventBus;
import org.dalvarez.ddd_example.shared.domain.category.DomainCategoryByIdFinder;
import org.dalvarez.ddd_example.shared.domain.criteria.CriteriaConverter;
import org.dalvarez.ddd_example.shared.domain.log.Logger;
import org.dalvarez.ddd_example.shared.domain.transaction_handler.TransactionHandler;
import org.dalvarez.ddd_example.shared.infrastructure.bus.DomainEventSubscribersInformation;
import org.dalvarez.ddd_example.shared.infrastructure.bus.kafka.publisher.KafkaEventBus;
import org.dalvarez.ddd_example.shared.infrastructure.bus.reactor.ReactorEventBus;
import org.dalvarez.ddd_example.shared.infrastructure.logger.Slf4jLogger;
import org.dalvarez.ddd_example.shared.infrastructure.persistence.hibernate.criteria.HibernateCriteriaConverter;
import org.dalvarez.ddd_example.shared.infrastructure.transaction_handler.SpringApplicationTransactionHandler;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;

import javax.persistence.EntityManager;

@Configuration
public class SharedModuleDependencyContainer {

    @Bean
    public DomainEventSubscribersInformation domainEventSubscribersInformation() {
        return new DomainEventSubscribersInformation();
    }

    @Bean
    @Primary
    public EventBus kafkaDomainEventBus(final KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        return new KafkaEventBus(kafkaTemplate);
    }

    @Bean
    public EventBus reactorDomainEventBus(final ApplicationContext applicationContext,
                                          final DomainEventSubscribersInformation domainEventSubscribersInformation) {
        return new ReactorEventBus(applicationContext, domainEventSubscribersInformation);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(final InjectionPoint ip) {
        return new Slf4jLogger(ip);
    }

    @Bean
    public CriteriaConverter<?> hibernateCriteriaConverter(final EntityManager entityManager) {
        return new HibernateCriteriaConverter<>(entityManager.getCriteriaBuilder());
    }

    @Bean
    public TransactionHandler springApplicationTransactionHandler() {
        return new SpringApplicationTransactionHandler();
    }

    @Bean
    public DomainCategoryByIdFinder domainCategoryByIdFinder(final CategoryRepository categoryRepository) {
        return new DomainCategoryByIdFinder(categoryRepository);
    }

}

