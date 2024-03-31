package cn.piao888.gateway.service.impl;

import cn.piao888.gateway.domain.modle.token.AccessToken;
import cn.piao888.gateway.domain.modle.token.AuthorizationCode;
import cn.piao888.gateway.domain.service.token.TokenExchangeService;
import cn.piao888.gateway.service.OAuth2ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ApplicationServiceImpl implements OAuth2ApplicationService {

    private final TokenExchangeService tokenExchangeService;

    @Autowired
    public OAuth2ApplicationServiceImpl(TokenExchangeService tokenExchangeService) {
        this.tokenExchangeService = tokenExchangeService;
    }

    public AccessToken exchangeCode(String code) {
        AuthorizationCode authorizationCode = new AuthorizationCode(code);
        return tokenExchangeService.exchangeAuthorizationCodeForAccessToken(authorizationCode);
    }
}