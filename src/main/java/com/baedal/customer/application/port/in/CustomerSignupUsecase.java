package com.baedal.customer.application.port.in;

public interface CustomerSignupUsecase {

  void signup(String email, String nickname, String rawPassword);
}
