package cn.piao888.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                // 禁用CSRF保护，因为通常在API服务中不需要
                .csrf().disable()
                // 为特定路径配置授权规则
                .authorizeExchange(exchanges -> exchanges
                        // 允许对/auth/authToken路径的匿名访问
                        .pathMatchers("/auth/authToken").permitAll()
                        // 其他请求都需要认证
                        .anyExchange().authenticated()
                )
                // 支持OAuth2登录
                .oauth2Login();

        // 构建并返回安全过滤器链
        return http.build();
    }
}
