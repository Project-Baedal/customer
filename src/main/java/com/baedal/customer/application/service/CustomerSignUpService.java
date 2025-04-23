package com.baedal.customer.application.service;

import com.baedal.customer.application.port.in.CustomerSignupUsecase;
import com.baedal.customer.domain.entity.Customer;
import com.baedal.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerSignUpService implements CustomerSignupUsecase {

  private final CustomerRepository repository;

  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signUp(String email, String name, String rawPassword) {
    Customer customer = new Customer(email, name, passwordEncoder.encode(rawPassword));
    repository.save(customer);
  }
}
