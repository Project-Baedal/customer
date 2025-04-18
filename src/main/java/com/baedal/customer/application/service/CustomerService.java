package com.baedal.customer.application.service;

import com.baedal.customer.application.port.dto.CustomerInfo;
import com.baedal.customer.domain.entity.Customer;
import com.baedal.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;

  @Transactional(readOnly = true)
  public CustomerInfo getCustomer(Long customerId) {
    Customer customer = repository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer 조회 실패"));
    return new CustomerInfo(customer.getId(), customer.getNickname());
  }
}
