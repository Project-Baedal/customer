package com.baedal.customer.adapter.presentation.controller;

import com.baedal.customer.adapter.presentation.request.LoginRequest;
import com.baedal.customer.adapter.presentation.request.SignupRequest;
import com.baedal.customer.adapter.presentation.response.GetCustomerResponse;
import com.baedal.customer.adapter.presentation.response.LoginResponse;
import com.baedal.customer.application.port.dto.CustomerInfo;
import com.baedal.customer.application.port.in.CustomerAuthenticationUsecase;
import com.baedal.customer.application.port.in.CustomerSignupUsecase;
import com.baedal.customer.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v0")
public class CustomerController {

  private final CustomerAuthenticationUsecase usecase;

  private final CustomerSignupUsecase customerSignupUsecase;

  private final CustomerService customerService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    LoginResponse response = usecase.authenticate(request.email(), request.password());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/signup")
  public ResponseEntity<Void> singup(@RequestBody SignupRequest request) {
    customerSignupUsecase.signup(request.email(), request.name(), request.password());
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<GetCustomerResponse> getCustomer(@AuthenticationPrincipal Long customerId) {
    CustomerInfo customer = customerService.getCustomer(customerId);
    GetCustomerResponse response = new GetCustomerResponse(customer.id(), customer.nickname());
    return ResponseEntity.ok(response);
  }
}
