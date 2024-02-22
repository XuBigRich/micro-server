package cn.piao888.oauth2.config;

import cn.piao888.oauth2.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  
 * </p>
 *
 * @author dugt1998@163.com
 * @date 2020/11/9 9:46
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }
 
    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }
}