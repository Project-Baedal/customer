package com.baedal.customer.application.port.in;

public interface CustomerSignupUsecase {

  void signUp(String email, String nickname, String rawPassword);
}
