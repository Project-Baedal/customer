package com.baedal.customer.adapter.web.controller;

import com.baedal.customer.adapter.web.request.LoginRequest;
import com.baedal.customer.adapter.web.request.SignUpRequest;
import com.baedal.customer.adapter.web.response.LoginResponse;
import com.baedal.customer.application.port.in.CustomerAuthenticationUseCase;
import com.baedal.customer.application.port.in.CustomerSignupUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/public")
public class CustomerAuthenticateController {

  private final CustomerAuthenticationUseCase authUseCase;

  private final CustomerSignupUsecase customerSignupUsecase;

  @PostMapping("/v0/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    LoginResponse response = authUseCase.authenticate(request.email(), request.password());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/v0/signup")
  public ResponseEntity<Void> singUp(@RequestBody SignUpRequest request) {
    customerSignupUsecase.signUp(request.email(), request.name(), request.password());
    return ResponseEntity.noContent().build();
  }
}
