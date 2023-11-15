package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Withdrawl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WithdrawRepo extends JpaRepository<Withdrawl, Long> {
}
