package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
/**
 * *   @TODO - add a query that finds all the bills with the associated customer Id
 *  @Query(value = "SELECT b.* FROM bills b JOIN customer c ON b.customer_id = c.customer_id  WHERE c.customer_id = ?1", nativeQuery = true)
 *       Set<Bill> findBillsByCustomertId(Long customerId);
 *
 * * @TODO - add a query that finds all the bills with the associated account Id
 *
 *       @Query(value = "SELECT w.* FROM bills  b JOIN account a ON b.account_id = a.account_id  WHERE a.account_id = ?1", nativeQuery = true)
 *     Set<Bill> findBillsByAccountId(Long accountId);
 *
 *
 *
 */
}
