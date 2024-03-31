package cn.piao888.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： hongzhi.xu
 * @Date: 2024/3/28 11:22
 * @Version 1.0
 */
@Configuration
@Data
public class AuthTokenConfig {
    @Value("${spring.security.oauth2.client.registration.myClient.clientId}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.myClient.clientSecret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.myClient.authorizationGrantType}")
    private String authorizationGrantType;
    @Value("${spring.security.oauth2.client.registration.myClient.redirectUri}")
    private String  redirectUri;
    @Value("${spring.security.oauth2.client.provider.myClient.token-uri}")
    private String tokenUri;
}
