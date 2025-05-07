package com.baedal.customer.application.port.in;

import com.baedal.customer.adapter.presentation.response.LoginResponse;

public interface CustomerAuthenticationUseCase {

  LoginResponse authenticate(String email, String password);
}
