//package cn.piao888.gatway.auth;
//
//import cn.hutool.core.collection.CollectionUtil;
//import cn.hutool.core.date.DateUtil;
//import cn.linglong.dmp.common.constant.SecurityConstants;
//import cn.linglong.dmp.common.enums.TypeEnum;
//import cn.linglong.dmp.common.enums.WhetherOrNotEnum;
//import cn.linglong.dmp.common.model.LtbUserEntity;
//import cn.linglong.dmp.common.model.PositionEntity;
//import cn.linglong.dmp.common.model.SysUser;
//import cn.piao888.common.vo.CurrentUserVo;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.Date;
//import java.util.Optional;
//
///**
// * 认证成功处理类
// *
// * @author dmp
// * @date 2019/10/7
// * <p>
// */
//public class Oauth2AuthSuccessHandler implements ServerAuthenticationSuccessHandler {
//    @Override
//    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
//        MultiValueMap<String, String> headerValues = new LinkedMultiValueMap(4);
//        Object principal = authentication.getPrincipal();
//        //客户端模式只返回一个clientId
//        if (principal instanceof CurrentUserVo) {
//            CurrentUserVo user = (CurrentUserVo) authentication.getPrincipal();
//            headerValues.add(SecurityConstants.USER_ID_HEADER, String.valueOf(user.getId()));
//            headerValues.add(SecurityConstants.USER_HEADER, user.getUsername());
//            try {
//                headerValues.add(SecurityConstants.USER_NAME_HEADER, URLEncoder.encode(user.getNickname(), "utf-8"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if(!StringUtils.isEmpty(user.getCrmId())){
//                headerValues.add(SecurityConstants.USER_CRM_ID,user.getCrmId());
//            }
//            if(!StringUtils.isEmpty(user.getCrmName())){
//                try {
//                    String crmName = URLEncoder.encode(user.getCrmName(), "utf-8");
//                    headerValues.add(SecurityConstants.USER_CRM_NAME,crmName);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//            //添加来源
//            headerValues.add(SecurityConstants.SYSTEM_TYPE_HEADER, user.getType());
//            //门店账号 设置岗位ID为用户ID
//            if(TypeEnum.TERM.getCode().equals(user.getType())||TypeEnum.CONSU.getCode().equals(user.getType())){
//                headerValues.add(SecurityConstants.POSITION_ID_HEADER, String.valueOf(user.getId()));
//                String positionName = null;
//                try {
//                    positionName = URLEncoder.encode(user.getUsername(), "utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                headerValues.add(SecurityConstants.POSITION_HEADER,positionName);
//            }else{
//                //主岗位
//                Optional<PositionEntity> first = user.getPositions().stream()
//                        .filter(o -> WhetherOrNotEnum.WHETHER.getCode().equals(o.getIsMain())).findFirst();
//                if(first.isPresent()) {
//                    PositionEntity positionEntity = first.get();
//                    headerValues.add(SecurityConstants.POSITION_ID_HEADER, String.valueOf(positionEntity.getId()));
//                    String positionName = null;
//                    try {
//                        positionName = URLEncoder.encode(positionEntity.getName(), "utf-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    headerValues.add(SecurityConstants.POSITION_HEADER,positionName);
//                }
//            }
//        } else if (principal instanceof LtbUserEntity) {
//            LtbUserEntity user = (LtbUserEntity) authentication.getPrincipal();
//            if (user.getId() != null) {
//                headerValues.add(SecurityConstants.USER_ID_HEADER, String.valueOf(user.getId()));
//            }
//            if (!StringUtils.isEmpty(user.getNickName())) {
//                headerValues.add(SecurityConstants.USER_HEADER, user.getNickName());
//            }
//        }
//        //请求时间
//        headerValues.add(SecurityConstants.REQUEST_DATE_HEADER, DateUtil.formatDateTime(new Date()));
//        OAuth2Authentication oauth2Authentication = (OAuth2Authentication)authentication;
//        String clientId = oauth2Authentication.getOAuth2Request().getClientId();
//        headerValues.add(SecurityConstants.TENANT_HEADER, clientId);
//        headerValues.add(SecurityConstants.ROLE_HEADER, CollectionUtil.join(authentication.getAuthorities(), ","));
//
//        ServerWebExchange exchange = webFilterExchange.getExchange();
//        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
//                .headers(h -> {
//                    h.addAll(headerValues);
//                })
//                .build();
//
//        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
//        return webFilterExchange.getChain().filter(build);
//    }
//}
