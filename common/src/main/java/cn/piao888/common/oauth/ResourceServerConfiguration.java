//package cn.piao888.common.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
//import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
//
///**
// * 资源服务器配置
// *
// * @author dmp
// * @date 2019/10/5
// * <p>
// */
//@Configuration
//public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .oauth2Client()
//                .and()
//                .oauth2Login();
//    }
//}
