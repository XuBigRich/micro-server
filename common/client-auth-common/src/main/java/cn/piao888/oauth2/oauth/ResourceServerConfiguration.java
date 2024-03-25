package cn.piao888.oauth2.oauth;//package cn.piao888.common.oauth;

import cn.piao888.oauth2.utils.SecurityUtils;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 资源服务器配置
 *
 * @author dmp
 * @date 2019/10/5
 * <p>
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception {
        resourcesServerSecurityFilterChain(http);
        authorityConfigure(http);
    }

    public void resourcesServerSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 当未登录时访问认证端点时重定向至login页面
                .exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
                // 处理使用access token访问用户信息端点和客户端注册端点
                .oauth2ResourceServer((resourceServer) ->
                        resourceServer.jwt(jet -> {
                                    try {
                                        jet
                                                .decoder(jwtDecoder())
                                                .jwtAuthenticationConverter(jwtAuthenticationConverter());
                                    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .accessDeniedHandler(SecurityUtils::exceptionHandler)
                                .authenticationEntryPoint(SecurityUtils::exceptionHandler));

    }

    public void authorityConfigure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests().antMatchers("/assets/**", "/webjars/**", "/login", "/business/dubbo/loginSuccessful")
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/assets/**"), new RegexRequestMatcher("/webjars/.*", HttpMethod.GET.name()))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutSuccessHandlerImpl()).permitAll()
                .and()
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
    }

    /**
     * 自定义jwt解析器，设置解析出来的权限信息的前缀与在jwt中的key
     *
     * @return jwt解析器 JwtAuthenticationConverter
     */
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // 设置解析权限信息的前缀，设置为空是去掉前缀
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        // 设置权限信息在jwt claims中的key
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }


    public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        return NimbusJwtDecoder.withJwkSetUri("https://example.com/.well-known/jwks.json").build();
        String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzT+lMxzjhPcIzn+mz/kJ1wq9GPyF6WADU4prUKPj1HrqDOgYWAllkG1EKS14dpy8obRxA1k2Kv/mnefCGaLvSsqZAh/Mgv5AxC9CdUnblfifaWdiRSuOjfWuDPA17d21L3qwdk3Q1tErgsBkFiTeryUzN2e+AmrqOoJTLKQrQutWsDwTzD5NAz9wCP06NyKZ4xFGgyJwqXEJY3kNuC+3+aDjhqB2tN+QzBCi3ItZDjNS0mPAFjI9VqSjyJj4wAjEpculYx/voB06FQ0TQHWQdOMedoPl6J9FPQcHEMQtletYfGjmIbK5B9lricTeQAFerODev3Sz65E2a5ayF4BgWQIDAQAB";
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKey = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyFactory.generatePublic(publicKey)).build();
    }
}
