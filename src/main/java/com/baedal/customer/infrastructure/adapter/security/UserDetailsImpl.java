package com.baedal.customer.infrastructure.adapter.security;

import com.baedal.customer.domain.entity.Customer;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

  private final Customer customer;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
        customer.getRole().getRole());
    ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(grantedAuthority);
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return customer.getPassword();
  }

  @Override
  public String getUsername() {
    return customer.getEmail();
  }
}
