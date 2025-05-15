package com.baedal.customer.application.service;

import com.baedal.customer.adapter.persistence.model.UserDetailsImpl;
import com.baedal.customer.adapter.persistence.entity.Customer;
import com.baedal.customer.adapter.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final CustomerRepository repository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer = repository.findByEmail(username).orElseThrow(
        () -> new BadCredentialsException("email or password is incorrect"));
    return new UserDetailsImpl(customer);
  }
}
