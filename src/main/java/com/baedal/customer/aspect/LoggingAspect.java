package com.baedal.customer.aspect;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Around("@within(org.springframework.web.bind.annotation.RestController)")
  public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    String methodName = method.getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String clientIp = getClientIp();

    log.info("[{}] in {} was called from {}", methodName, className, clientIp);
    try {
      Object result = joinPoint.proceed();
      log.info("[{}] in {} returned {}", methodName, className, result);
      return result;
    } catch (Throwable e) {
      String errorClassName = e.getClass().getSimpleName();
      String errorMessage = e.getMessage();
      log.warn("[{}] in [{}] threw exception [{}]. message: [{}]",
          methodName, className, errorClassName, errorMessage);
      throw e;
    }
  }

  private String getClientIp() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes == null) {
      return "Unknown IP";
    }

    HttpServletRequest request = attributes.getRequest();
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    if ("0:0:0:0:0:0:0:1".equals(ip)) {
      return "localhost";
    }
    return ip;
  }

}
