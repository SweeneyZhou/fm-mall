package com.sweeney.mall.config;

import com.sweeney.mall.intercepter.CheckLoginInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sweeney
 * @since 2021/07/01 16:08 created.
 */
@AllArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final CheckLoginInterceptor checkLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginInterceptor)
                // "/*"对该拦截器对所有的请求都拦截
                .addPathPatterns("/shopcart/**")
                .addPathPatterns("/orders/**")
                .addPathPatterns("/useraddr/**")
                //为了测试该拦截器提供的测试接口
                .addPathPatterns("/user/check");
    }
}
