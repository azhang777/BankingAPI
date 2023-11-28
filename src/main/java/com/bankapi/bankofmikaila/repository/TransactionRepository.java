package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Deposit;
import com.bankapi.bankofmikaila.model.Transaction;
import com.bankapi.bankofmikaila.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query(value = "SELECT t.* FROM transaction t JOIN account a ON t.account_id = a.account_id  WHERE a.account_id = ?1", nativeQuery = true)
    Iterable<Transaction> getAllTransactionsByAID(Long accountId);
    @Query(value = "SELECT * FROM transaction WHERE transaction_type = 'WITHDRAWAL'", nativeQuery = true)
    Iterable<Withdrawal> getAllWithdrawalsByAID(Long accountId);
    @Query(value = "SELECT * FROM transaction WHERE transaction_type = 'DEPOSIT'", nativeQuery = true)
    Iterable<Deposit> getAllDepositsByAID(Long accountId);

    @Query(value = "DELETE FROM transaction WHERE transaction_id = ?1", nativeQuery = true)
    int deleteTransactionById(Long tid);
}
