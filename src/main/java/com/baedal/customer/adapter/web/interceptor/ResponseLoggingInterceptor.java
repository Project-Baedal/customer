package com.baedal.customer.adapter.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ResponseLoggingInterceptor implements HandlerInterceptor {

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return;
    }

    if (ex != null) {
      logUnHandledException(ex);
    }

    int status = response.getStatus();
    log.info("Response Status:: [{}]", status);
  }

  private void logUnHandledException(Exception ex) {
    log.warn("Exception: [{}].[{}]", ex.getClass().getSimpleName(), ex.getMessage());
  }
}
