package com.baedal.customer.presentation.controller;

import com.baedal.customer.application.service.CustomerAuthenticationService;
import com.baedal.customer.application.service.CustomerSignupService;
import com.baedal.customer.presentation.request.LoginRequest;
import com.baedal.customer.presentation.request.SignupRequest;
import com.baedal.customer.presentation.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

  private final CustomerAuthenticationService customerAuthenticationService;

  private final CustomerSignupService customerSignupService;

  @PostMapping("/v0/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    String token = customerAuthenticationService.authenticate(request.email(), request.password());
    LoginResponse response = new LoginResponse(token);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/v0/signup")
  public ResponseEntity<Void> singup(@RequestBody SignupRequest request) {
    customerSignupService.signup(request.email(), request.nickname(), request.password());
    return ResponseEntity.noContent().build();
  }
}
