package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Withdrawl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface WithdrawRepo extends JpaRepository<Withdrawl, Long> {

    @Query("SELECT w FROM Withdrawl w JOIN w.account a WHERE a.id = :accountId")
    Set<Withdrawl> findWithdrawlsByAccountId(Long accountId);
}
