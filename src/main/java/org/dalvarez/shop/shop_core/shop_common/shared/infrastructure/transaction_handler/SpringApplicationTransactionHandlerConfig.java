package org.dalvarez.shop.shop_core.shop_common.shared.infrastructure.transaction_handler;

import org.dalvarez.shop.shop_core.shop_common.shared.domain.transaction_handler.TransactionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationTransactionHandlerConfig {

    public static final String BEAN_NAME = "spring_application-transaction_handler";

    @Bean
    @Qualifier(BEAN_NAME)
    public TransactionHandler springApplicationTransactionHandler() {
        return new SpringApplicationTransactionHandler();
    }

}
