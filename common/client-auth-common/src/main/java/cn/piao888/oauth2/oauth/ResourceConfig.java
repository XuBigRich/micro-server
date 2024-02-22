package cn.piao888.oauth2.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ResourceConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler).permitAll().and()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
    }
}
