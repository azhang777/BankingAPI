package com.bankapi.bankofmikaila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bankapi.bankofmikaila.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Iterable<Account> findByCustomer_Id(Long customerId);

    @Transactional
    void updateBalance(Long accountId, Double newBalance);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET balance = balance - :amount WHERE id = :payerAccount_id", nativeQuery = true)
    void deductBalance(@Param("payerAccount_id") Long senderAccount_id, @Param("amount") Double amount);


    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET balance = balance + :amount WHERE id = :payeeAccount_id", nativeQuery = true)
    void addBalance(@Param("payeeAccount_id") Long receiverAccount_id, @Param("amount") Double amount);

}
