package com.baedal.customer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {

  CUSTOMER("ROLE_CUSTOMER"),
  RIDER("ROLE_RIDER"),
  OWNER("ROLE_OWNER");

  private final String role;
}
