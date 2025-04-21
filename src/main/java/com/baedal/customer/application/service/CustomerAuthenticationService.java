package com.baedal.customer.application.service;

import com.baedal.customer.adapter.presentation.response.LoginResponse;
import com.baedal.customer.adapter.presentation.security.UserDetailsImpl;
import com.baedal.customer.application.port.in.CustomerAuthenticationUsecase;
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
public class CustomerAuthenticationService implements CustomerAuthenticationUsecase {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;

  @Transactional(readOnly = true)
  public LoginResponse authenticate(String email, String password) {
    UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      // FIXME: Define Exception Class
      throw new IllegalArgumentException("Invalid email or password");
    }

    return new LoginResponse(
        user.customer().getId()
    );
  }

  private String getAuthority(UserDetails user) {
    return user.getAuthorities().iterator().next().getAuthority();
  }
}
