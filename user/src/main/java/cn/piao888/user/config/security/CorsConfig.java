package cn.piao888.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // 设置访问源地址
//        config.addAllowedOriginPattern("*");
//        // 设置访问源请求头
//        config.addAllowedHeader("*");
//        // 设置访问源请求方法
//        config.addAllowedMethod("*");
//        // 对接口配置跨域设置
//        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}