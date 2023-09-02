package cn.piao888.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication(scanBasePackages = {"org.springframework.security.oauth2.config.annotation.configuration", "cn.piao888.user"})
//@SpringBootApplication
@EnableAuthorizationServer
@EnableWebSecurity
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
