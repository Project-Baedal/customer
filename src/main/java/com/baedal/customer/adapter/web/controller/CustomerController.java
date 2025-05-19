package com.baedal.customer.adapter.web.controller;

import com.baedal.customer.adapter.web.mapper.CustomerWebMapper;
import com.baedal.customer.adapter.web.response.GetCustomerResponse;
import com.baedal.customer.adapter.web.response.GetCustomersResponse;
import com.baedal.customer.application.port.dto.CustomerInfo;
import com.baedal.customer.application.service.CustomerService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/customer")
public class CustomerController {

  private final CustomerService service;

  private final CustomerWebMapper mapper;

  // FIXME: 자신의 프로필 조회로 변경
  @GetMapping("/v0/profile")
  public ResponseEntity<GetCustomerResponse> getMyProfile(
      @AuthenticationPrincipal Long customerId) {
    CustomerInfo customer = service.getCustomer(customerId);
    GetCustomerResponse response = mapper.toGetCustomerResponse(customer);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/v0/reviewInfos/{customerId}")
  public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable Long customerId) {
    CustomerInfo customer = service.getCustomer(customerId);
    GetCustomerResponse response = mapper.toGetCustomerResponse(customer);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/v0/reviewInfos")
  public ResponseEntity<GetCustomersResponse> getCustomers(@RequestParam Collection<Long> ids) {
    Collection<CustomerInfo> customers = service.getCustomers(ids);
    GetCustomersResponse response = mapper.toGetCustomersResponse(customers);
    return ResponseEntity.ok(response);
  }
}
