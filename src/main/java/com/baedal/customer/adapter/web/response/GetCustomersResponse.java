package com.baedal.customer.adapter.web.response;

import java.util.Collection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCustomersResponse {

  Collection<GetCustomerResponse> data;
}
