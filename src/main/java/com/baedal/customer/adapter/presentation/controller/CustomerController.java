package com.baedal.customer.adapter.presentation.controller;

import com.baedal.customer.adapter.presentation.request.LoginRequest;
import com.baedal.customer.adapter.presentation.request.SignupRequest;
import com.baedal.customer.adapter.presentation.response.LoginResponse;
import com.baedal.customer.application.port.in.CustomerAuthenticationUsecase;
import com.baedal.customer.application.port.in.CustomerSignupUsecase;
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

  private final CustomerAuthenticationUsecase customerAuthenticationUsecase;

  private final CustomerSignupUsecase customerSignupUsecase;

  @PostMapping("/v0/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    LoginResponse response = customerAuthenticationUsecase.authenticate(request.email(),
        request.password());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/v0/signup")
  public ResponseEntity<Void> singup(@RequestBody SignupRequest request) {
    customerSignupUsecase.signup(request.email(), request.nickname(), request.password());
    return ResponseEntity.noContent().build();
  }
}
