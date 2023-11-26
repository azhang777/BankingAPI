package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Withdrawl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface WithdrawRepo extends JpaRepository<Withdrawl, Long> {

    @Query(value = "SELECT w.* FROM withdrawals w JOIN account a ON w.account_id = a.account_id  WHERE a.account_id = ?1", nativeQuery = true)
    Set<Withdrawl> findWithdrawlsByAccountId(Long accountId);
}
