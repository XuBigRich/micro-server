package cn.piao888.gateway.controller;

import cn.piao888.gateway.domain.modle.token.AccessToken;
import cn.piao888.gateway.service.OAuth2ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： hongzhi.xu
 * @Date: 2024/3/27 20:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public OAuth2ApplicationService oAuth2ApplicationService;

    @GetMapping("/authToken")
    public AccessToken authToken(String code) {
        final AccessToken accessToken = oAuth2ApplicationService.exchangeCode(code);
        return accessToken;
    }
}
