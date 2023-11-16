package com.bankapi.bankofmikaila.repository;

import com.bankapi.bankofmikaila.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
