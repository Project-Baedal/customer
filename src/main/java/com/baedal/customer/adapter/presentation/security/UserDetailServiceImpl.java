package com.baedal.customer.adapter.presentation.security;

import com.baedal.customer.domain.entity.Customer;
import com.baedal.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final CustomerRepository repository;

  /**
   * @param username the username identifying the user whose data is required.
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer = repository.findByEmail(username).orElseThrow(
        () -> new UsernameNotFoundException(String.format("이메일을 찾을 수 없습니다. :[%s]", username)));
    return new UserDetailsImpl(customer);
  }
}
