package com.baedal.customer.application.service;

import com.baedal.customer.application.port_out.TokenServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerAuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;
  private final TokenServicePort tokenServicePort;

  @Transactional(readOnly = true)
  public String authenticate(String email, String password) {
    UserDetails user = userDetailsService.loadUserByUsername(email);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      // FIXME: Define Exception Class
      throw new IllegalArgumentException("Invalid email or password");
    }

    return tokenServicePort.requestToken(email, getAuthority(user));
  }

  private String getAuthority(UserDetails user) {
    return user.getAuthorities().iterator().next().getAuthority();
  }
}
