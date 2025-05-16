package com.baedal.customer.adapter.web.mapper;

import com.baedal.customer.adapter.web.response.ErrorResponse;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.springframework.security.authentication.BadCredentialsException;

@Mapper(componentModel = "spring")
public interface ExceptionWebMapper {

  default ErrorResponse toErrorResponse(BadCredentialsException ex, String code, String path) {
    return ErrorResponse.builder()
        .status(code)
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now())
        .path(path)
        .build();
  }
}
