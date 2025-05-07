package com.baedal.customer.adapter.presentation.controller;

import com.baedal.customer.adapter.presentation.request.LoginRequest;
import com.baedal.customer.adapter.presentation.request.SignUpRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/customer/v0")
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
  public ResponseEntity<Void> singUp(@RequestBody SignUpRequest request) {
    customerSignupUsecase.signUp(request.email(), request.name(), request.password());
    return ResponseEntity.noContent().build();
  }

  // FIXME: 자신의 프로필 조회로 변경
  @GetMapping("/profile")
  public ResponseEntity<GetCustomerResponse> getMyProfile(
      @AuthenticationPrincipal Long customerId) {
    CustomerInfo customer = customerService.getCustomer(customerId);
    GetCustomerResponse response = new GetCustomerResponse(customer.id(), customer.name());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/reviewInfo/{customerId}")
  public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable Long customerId) {
    CustomerInfo customer = customerService.getCustomer(customerId);
    GetCustomerResponse response = new GetCustomerResponse(customer.id(), customer.name());
    return ResponseEntity.ok(response);
  }
}
