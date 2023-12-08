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


}
