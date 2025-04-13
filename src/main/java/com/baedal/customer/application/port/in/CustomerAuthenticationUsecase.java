package com.baedal.customer.application.port.in;

public interface CustomerAuthenticationUsecase {

  String authenticate(String email, String password);
}
