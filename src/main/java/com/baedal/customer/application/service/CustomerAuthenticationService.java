package com.baedal.customer.application.service;

import com.baedal.customer.adapter.web.response.LoginResponse;
import com.baedal.customer.adapter.web.security.UserDetailsImpl;
import com.baedal.customer.application.mapper.CustomerApplicationMapper;
import com.baedal.customer.application.port.in.CustomerAuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerAuthenticationService implements CustomerAuthenticationUseCase {

  private final PasswordEncoder passwordEncoder;

  private final UserDetailsService userDetailsService;

  private final CustomerApplicationMapper mapper;

  @Transactional(readOnly = true)
  public LoginResponse authenticate(String email, String password) {
    UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("email or password is incorrect");
    }

    return mapper.toResponse(user.customer());
  }

  private String getAuthority(UserDetails user) {
    return user.getAuthorities().iterator().next().getAuthority();
  }
}
