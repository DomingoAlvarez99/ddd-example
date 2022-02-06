package org.dalvarez.ddd_example.shared.infrastructure.transaction_handler;

import org.dalvarez.ddd_example.shared.domain.transaction_handler.TransactionHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public class SpringApplicationTransactionHandler implements TransactionHandler {

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public <T> T runInTransaction(final Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void runInTransaction(final Runnable runnable) {
        runnable.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public <T> T runInNewTransaction(final Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void runInNewTransaction(final Runnable runnable) {
        runnable.run();
    }

}
