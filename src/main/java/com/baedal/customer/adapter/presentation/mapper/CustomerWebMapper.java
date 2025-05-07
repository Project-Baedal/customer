package com.baedal.customer.adapter.presentation.mapper;

import com.baedal.customer.adapter.presentation.response.GetCustomerResponse;
import com.baedal.customer.adapter.presentation.response.GetCustomersResponse;
import com.baedal.customer.application.port.dto.CustomerInfo;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {

  @Mapping(target = "customerId", source = "id")
  GetCustomerResponse toGetCustomerResponse(CustomerInfo info);

  @Mapping(target = "customerId", source = "id")
  Collection<GetCustomerResponse> toCustomerResponses(Collection<CustomerInfo> infos);

  default GetCustomersResponse toGetCustomersResponse(Collection<CustomerInfo> infos) {
    Collection<GetCustomerResponse> data = toCustomerResponses(infos);
    return GetCustomersResponse.builder()
        .data(data)
        .build();
  }
}
