package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
