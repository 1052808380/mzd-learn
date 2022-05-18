package org.mzd.eye.interceptor;

import org.mzd.eye.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2022/5/18 下午1:57
 */
@Configuration
public class WebInterceptorAdapter implements WebMvcConfigurer {
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor());
    }
}