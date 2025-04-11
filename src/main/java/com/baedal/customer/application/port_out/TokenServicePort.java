package com.baedal.customer.application.port_out;

public interface TokenServicePort {

  String requestToken(String email, String role);

  Boolean validateToken(String token);
}
