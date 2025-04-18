package com.baedal.customer.domain.repository;

import com.baedal.customer.domain.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByEmail(String email);
}
