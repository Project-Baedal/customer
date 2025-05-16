package com.baedal.customer.config;

import com.baedal.customer.adapter.web.filter.RequestAccessLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  @Bean
  public FilterRegistrationBean<RequestAccessLoggingFilter> accessLoggingFilter() {
    RequestAccessLoggingFilter filter = new RequestAccessLoggingFilter();

    FilterRegistrationBean<RequestAccessLoggingFilter> reg = new FilterRegistrationBean<>();
    reg.setOrder(2);
    reg.setFilter(filter);
    reg.addUrlPatterns("/*");
    return reg;
  }
}
