//package com.bankapi.bankofmikaila.repository;
//
//import com.bankapi.bankofmikaila.model.Deposit;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Set;
//
//@Repository
//public interface DepositRepository extends JpaRepository<Deposit, Long> {
//
//    @Query(value = "SELECT t.* FROM transaction t JOIN account a ON t.transaction_account_id WHERE a.account_id = ?1", nativeQuery = true)
//    Set<Deposit> findDepositsByAccountId(Long accountId);
//
//}
