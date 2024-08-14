package cn.piao888.gateway.service;

import cn.piao888.gateway.domain.modle.token.AccessToken;


public interface OAuth2ApplicationService {
    AccessToken exchangeCode(String code);

    AccessToken refreshToken(String refreshToken);
}