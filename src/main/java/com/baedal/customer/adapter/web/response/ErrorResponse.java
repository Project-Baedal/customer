package com.baedal.customer.adapter.web.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ErrorResponse {

  private String status;
  private String message;
  private LocalDateTime timestamp;
  private String path;
}
