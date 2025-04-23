package com.baedal.customer.application.port.in;

public interface CustomerSignupUsecase {

  void signUp(String email, String name, String rawPassword);
}
