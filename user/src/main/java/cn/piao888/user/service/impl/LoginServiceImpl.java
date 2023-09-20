package cn.piao888.user.service.impl;

import cn.piao888.common.vo.CurrentUserVo;
//import cn.piao888.user.security.UserInfo;
import cn.piao888.user.security.UserInfo;
import cn.piao888.user.service.LoginService;
import cn.piao888.user.vo.req.LoginBody;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/5/29 7:31 下午
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices tokenServices;
    @Autowired
    private TokenStore tokenStore;


    @Override
    public CurrentUserVo login(LoginBody loginBody) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
        final Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //后台存储的token
        UserInfo loginUser = (UserInfo) authenticate.getPrincipal();
        CurrentUserVo currentUserVo = new CurrentUserVo();
        currentUserVo.setUserName(loginUser.getUserName());
        currentUserVo.setId(loginUser.getId());
        currentUserVo.setNickName(loginUser.getNickName());
        // 在登录成功后生成自定义的Access Token
        OAuth2AccessToken customAccessToken = generateCustomAccessToken(loginBody.getClientId(), usernamePasswordAuthenticationToken);
        currentUserVo.setToken(customAccessToken.getValue());
        //传给前端的数据
        return currentUserVo;
    }

    // 自定义生成Access Token的方法
    private OAuth2AccessToken generateCustomAccessToken(String clientId, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(
                createOAuth2Request(clientId),
                usernamePasswordAuthenticationToken);
        return tokenServices.createAccessToken(oAuth2Authentication);
    }

    private OAuth2Request createOAuth2Request(String clientId) {
        final ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        return new OAuth2Request(
                null, clientId, clientDetails.getAuthorities(),
                true, clientDetails.getScope(), clientDetails.getResourceIds(), clientDetails.getRegisteredRedirectUri().toString(), clientDetails.getAuthorizedGrantTypes(), null
        );
    }

    public void getToken(String token) {
        final OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        final OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
        System.out.println(oAuth2AccessToken.getValue());
    }


}
