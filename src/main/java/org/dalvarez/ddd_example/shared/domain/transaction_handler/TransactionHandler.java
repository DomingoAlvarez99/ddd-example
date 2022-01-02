package org.dalvarez.ddd_example.shared.domain.transaction_handler;

import java.util.function.Supplier;

public interface TransactionHandler {

    <T> T runInTransaction(Supplier<T> supplier);

    void runInTransaction(Runnable runnable);

    <T> T runInNewTransaction(Supplier<T> supplier);

    void runInNewTransaction(Runnable runnable);

}
