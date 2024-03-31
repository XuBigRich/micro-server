//package cn.piao888.gatway.auth;
//
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
//import reactor.core.publisher.Mono;
///**
// * @author dmp
// * @date 2019/10/6
// * <p>
// */
//public class CustomAuthenticationManager implements ReactiveAuthenticationManager {
//    private JwtTokenStore tokenStore;
//
//    public CustomAuthenticationManager(TokenStore tokenStore) {
//        this.tokenStore = tokenStore;
//    }
//
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        return Mono.justOrEmpty(authentication)
//                .filter(a -> a instanceof BearerTokenAuthenticationToken)
//                .cast(BearerTokenAuthenticationToken.class)
//                .map(BearerTokenAuthenticationToken::getToken)
//                .flatMap((accessTokenValue -> {
//                    OAuth2AccessToken accessToken = tokenStore.readAccessToken(accessTokenValue);
//                    if (accessToken == null) {
//                        return Mono.error(new Oauth2AuthFailException("登陆失效，请重新登陆" ));
//                    } else if (accessToken.isExpired()) {
//                        tokenStore.removeAccessToken(accessToken);
//                        return Mono.error(new Oauth2AuthFailException("登陆失效，请重新登陆"));
//                    }
//
//                    OAuth2Authentication result = tokenStore.readAuthentication(accessToken);
//                    if (result == null) {
//                        return Mono.error(new Oauth2AuthFailException("登陆失效，请重新登陆"));
//                    }
//                    return Mono.just(result);
//                }))
//                .cast(Authentication.class);
//    }
//}
