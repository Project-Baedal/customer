package com.baedal.customer.adapter.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCustomerResponse {

  private Long customerId;
  private String name;
}
