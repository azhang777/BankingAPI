package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @Query(value = "SELECT * FROM transaction WHERE type = 'WITHDRAWAL'", nativeQuery = true)
//    Iterable<Transaction> getAllWithdrawalsByAID(Long accountId);
//    @Query(value = "SELECT * FROM transaction WHERE type = 'DEPOSIT'", nativeQuery = true)
//    Iterable<Transaction> getAllDepositsByAID(Long accountId);

    @Query(value = "SELECT t.* FROM transaction t JOIN account a ON t.account_id = a.account_id  WHERE a.account_id = ?1", nativeQuery = true)
    Iterable<Transaction> getAllTransactionsByAID(Long accountId);
    @Query(value = "SELECT t.* FROM transaction t JOIN account a ON t.account1_id = a.account_id WHERE a.account_id = ?1 AND t.type = 'WITHDRAWAL'", nativeQuery = true)
    Set<Transaction> getAllWithdrawalsByAID(Long accountId);
    @Query(value = "SELECT t.* FROM transaction t JOIN account a ON t.account1_id = a.account_id WHERE a.account_id = ?1 AND t.type = 'DEPOSIT'", nativeQuery = true)
    Set<Transaction> getAllDepositsByAID(Long accountId);

    @Query(value = "SELECT * FROM transaction WHERE type = 'P2P'", nativeQuery = true)
    Iterable<Transaction> getAllP2PByAID(Long accountId);
    @Query(value = "DELETE FROM transaction WHERE transaction_id = ?1", nativeQuery = true)
    int deleteTransactionById(Long tid);
}
