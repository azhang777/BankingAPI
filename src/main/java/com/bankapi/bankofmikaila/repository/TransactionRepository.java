package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query(value = "SELECT t.* FROM transactions t JOIN account a ON t.account_id = a.account_id  WHERE a.account_id = ?1", nativeQuery = true)
    Iterable<Transaction> getAllTransactionsByAID(Long accountId);
    @Query(value = "SELECT * FROM transactions WHERE type = 'WITHDRAWAL'", nativeQuery = true)
    Iterable<Transaction> getAllWithdrawalsByAID(Long accountId);
    @Query(value = "SELECT * FROM transactions WHERE type = 'DEPOSIT'", nativeQuery = true)
    Iterable<Transaction> getAllDepositsByAID(Long accountId);

    @Query(value = "DELETE FROM transactions  WHERE transaction_id = ?1", nativeQuery = true)
    int deleteTransactionById(Long tid);
}
