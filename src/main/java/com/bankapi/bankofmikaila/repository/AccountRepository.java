package com.bankapi.bankofmikaila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bankapi.bankofmikaila.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
