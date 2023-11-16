package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
