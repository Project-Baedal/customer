package com.baedal.customer.infrastructure.adapter.security;

import com.baedal.customer.application.port_out.TokenServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenServiceAdapter implements TokenServicePort {

  // TODO: Gateway 서비스로 부터 토근 생성 및 검증
//  private final RestClient restClient;

  @Override
  public String requestToken(String email, String role) {
    // FIXME: Mocking
    return email + role;
  }

  @Override
  public Boolean validateToken(String token) {
    // FIXME: Mocking
    return true;
  }
}
