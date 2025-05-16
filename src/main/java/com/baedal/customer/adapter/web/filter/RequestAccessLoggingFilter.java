package com.baedal.customer.adapter.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class RequestAccessLoggingFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    String method = request.getMethod();
    String ip = getClientIp(request);
    String uri = request.getRequestURI();

    log.info("Before Request:: Client IP: [{}], [{}] path:[{}]", ip, method, uri);
    try {
      filterChain.doFilter(request, response);
    } catch (Throwable e) {
      throw e;
    } finally {
      log.info("After Request:: Client IP: [{}], [{}] path:[{}]", ip, method, uri);
    }
  }

  private String getClientIp(HttpServletRequest req) {
    String ip = req.getHeader("X-Forwarded-For");
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = req.getRemoteAddr();
    }
    if ("0:0:0:0:0:0:0:1".equals(ip)) {
      return "localhost";
    }
    return ip;
  }

  private String getHeaders(HttpServletRequest req) {
    throw new RuntimeException("Not Implemented Yet");
  }
}
