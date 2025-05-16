package com.baedal.customer.config;

import com.baedal.customer.adapter.web.filter.RequestAccessLoggingFilter;
import com.baedal.customer.adapter.web.interceptor.ResponseLoggingInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean<RequestAccessLoggingFilter> accessLoggingFilter() {
    RequestAccessLoggingFilter filter = new RequestAccessLoggingFilter();

    FilterRegistrationBean<RequestAccessLoggingFilter> reg = new FilterRegistrationBean<>();
    reg.setOrder(2);
    reg.setFilter(filter);
    reg.addUrlPatterns("/*");
    return reg;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ResponseLoggingInterceptor())
        .order(1)
        .excludePathPatterns("/error");  // 에러 페이지 제외

//        .addPathPatterns("/**"); // 모든 URI에 적용
  }
}
