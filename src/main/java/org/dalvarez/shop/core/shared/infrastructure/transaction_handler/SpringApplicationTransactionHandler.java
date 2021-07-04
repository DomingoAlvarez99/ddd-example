package org.dalvarez.shop.core.shared.infrastructure.transaction_handler;

import org.dalvarez.shop.core.shared.domain.transaction_handler.TransactionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class SpringApplicationTransactionHandler implements TransactionHandler {

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public <T> T runInTransaction(final Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void runInTransaction(final Runnable runnable) {
        runnable.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public <T> T runInNewTransaction(final Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void runInNewTransaction(final Runnable runnable) {
        runnable.run();
    }

}
