package cn.piao888.gateway.domain.service.token;

import cn.piao888.gateway.config.AuthTokenConfig;
import cn.piao888.gateway.domain.modle.token.AccessToken;
import cn.piao888.gateway.domain.modle.token.AuthorizationCode;
import org.apache.http.impl.io.ChunkedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import reactor.netty.http.client.HttpClient;

import java.util.List;

@Service
public class TokenExchangeService {

    private final RestTemplate restTemplate;
    private AuthTokenConfig authTokenConfig;

    @Autowired
    public TokenExchangeService(AuthTokenConfig authTokenConfig) {
        this.authTokenConfig = authTokenConfig;
        this.restTemplate = new RestTemplate();
        final List<HttpMessageConverter<?>> messageConverters = this.restTemplate.getMessageConverters();
        messageConverters.add(0,new OAuth2AccessTokenResponseHttpMessageConverter());
    }

    public AccessToken exchangeAuthorizationCodeForAccessToken(AuthorizationCode authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(OAuth2ParameterNames.GRANT_TYPE, authTokenConfig.getAuthorizationGrantType());
        params.add(OAuth2ParameterNames.CODE, authorizationCode.getCode());
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

        OAuth2AccessTokenResponse tokenResponse = responseEntity.getBody();

        // 假设我们的OAuth2服务器响应的是一个兼容的OAuth2AccessTokenResponse对象
        // 确保将OAuth2AccessTokenResponse转换为你的领域模型AccessToken
        return convertToDomainAccessToken(tokenResponse);
    }

    private AccessToken convertToDomainAccessToken(OAuth2AccessTokenResponse oAuth2AccessTokenResponse) {
        if (oAuth2AccessTokenResponse == null) {
            throw new AuthenticationServiceException("Token response cannot be null");
        }

        // 从OAuth2AccessTokenResponse中提取必要的数据转换为我们的领域模型AccessToken
        OAuth2AccessToken oAuth2AccessToken = oAuth2AccessTokenResponse.getAccessToken();

        AccessToken accessToken = new AccessToken(oAuth2AccessToken.getTokenValue(), oAuth2AccessTokenResponse.getRefreshToken().getTokenValue(), oAuth2AccessToken.getExpiresAt().getEpochSecond());
        return accessToken;
    }
}