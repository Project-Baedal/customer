package com.baedal.customer.application.port.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerInfo {

  private Long id;
  private String name;
}
