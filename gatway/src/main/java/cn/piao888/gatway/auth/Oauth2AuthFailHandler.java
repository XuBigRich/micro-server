//package cn.piao888.gatway.auth;
//
//import cn.linglong.dmp.common.utils.ResponseUtil;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
//import reactor.core.publisher.Mono;
//
///**
// * @Project dmp-platform
// * @PackageName cn.linglong.dmp.gateway.config
// * @ClassName Oauth2AuthFailHandler
// * @Author HuangLong
// * @Date 2020/3/27 15:44
// * @Description TODO
// */
//public class Oauth2AuthFailHandler implements ServerAuthenticationFailureHandler {
//
//    @Override
//    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
//        return ResponseUtil.responseWriter(webFilterExchange.getExchange(), HttpStatus.UNAUTHORIZED.value(), e.getMessage());
//    }
//
//}
