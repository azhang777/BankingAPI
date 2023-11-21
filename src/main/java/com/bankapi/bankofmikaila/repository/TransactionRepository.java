package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t FROM transactions t JOIN t.account a WHERE a.id = ?1", nativeQuery = true)
    Iterable<Transaction> getAllTransactionsByAID(Long accountId);
}
