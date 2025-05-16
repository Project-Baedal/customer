package com.baedal.customer.adapter.web.advice;

import com.baedal.customer.adapter.web.mapper.ExceptionWebMapper;
import com.baedal.customer.adapter.web.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionAdvice {

  private final ExceptionWebMapper mapper;

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(
      BadCredentialsException ex,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String path = request.getRequestURI();
    ErrorResponse errorResponse = mapper.toErrorResponse(ex, status.toString(), path);

    if (log.isDebugEnabled()) {
      log.debug("BadCredentialsException: message:[{}]. body:[{}]", ex.getMessage(), errorResponse);
    }

    return ResponseEntity
        .badRequest()
        .body(errorResponse);
  }
}
