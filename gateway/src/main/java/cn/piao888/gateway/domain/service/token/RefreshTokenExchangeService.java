package cn.piao888.gateway.domain.service.token;

import cn.piao888.gateway.config.AuthTokenConfig;
import cn.piao888.gateway.domain.modle.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author： hongzhi.xu
 * @Date: 2024/4/2 19:39
 * @Version 1.0
 */
@Service
public class RefreshTokenExchangeService {
    private final RestTemplate restTemplate;
    private AuthTokenConfig authTokenConfig;

    @Autowired
    public RefreshTokenExchangeService(AuthTokenConfig authTokenConfig) {
        this.authTokenConfig = authTokenConfig;
        this.restTemplate = new RestTemplate();
        final List<HttpMessageConverter<?>> messageConverters = this.restTemplate.getMessageConverters();
        messageConverters.add(0, new OAuth2AccessTokenResponseHttpMessageConverter());
    }

    public AccessToken refreshToken(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(OAuth2ParameterNames.GRANT_TYPE, authTokenConfig.getAuthorizationGrantType());
//        params.add(OAuth2ParameterNames.REFRESH_TOKEN, authorizationCode.getCode());
        params.add(OAuth2ParameterNames.REDIRECT_URI, authTokenConfig.getRedirectUri());
        params.add(OAuth2ParameterNames.CLIENT_ID, authTokenConfig.getClientId());
        // 通常client_secret应该使用Client Credentials Grant进行管理, 但为了示例的简洁性这里直接包含了
        params.add(OAuth2ParameterNames.CLIENT_SECRET, authTokenConfig.getClientSecret());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<OAuth2AccessTokenResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    authTokenConfig.getTokenUri(),
                    HttpMethod.POST,
                    requestEntity,
                    OAuth2AccessTokenResponse.class
            );
        } catch (RestClientException e) {
            throw new AuthenticationServiceException("Error during authorization code exchange", e);
        }
        return null;
    }
}
