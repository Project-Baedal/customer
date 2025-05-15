package com.baedal.customer.application.mapper;

import com.baedal.customer.adapter.web.response.LoginResponse;
import com.baedal.customer.application.port.dto.CustomerInfo;
import com.baedal.customer.adapter.persistence.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerApplicationMapper {

  CustomerInfo entityToInfo(Customer customer);

  LoginResponse toResponse(Customer customer);
}
