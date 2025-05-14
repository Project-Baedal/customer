package com.baedal.customer.application.mapper;

import com.baedal.customer.application.port.dto.CustomerInfo;
import com.baedal.customer.domain.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerApplicationMapper {

  CustomerInfo entityToInfo(Customer customer);
}
