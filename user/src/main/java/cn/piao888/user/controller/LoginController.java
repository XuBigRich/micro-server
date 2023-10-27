package cn.piao888.user.controller;

import cn.piao888.common.response.ObjectResponse;
import cn.piao888.common.utils.SessionUtil;
import cn.piao888.common.vo.CurrentUserVo;
import cn.piao888.user.service.LoginService;
import cn.piao888.user.vo.req.LoginBody;
import cn.piao888.user.vo.req.OauthGrantReq;
import cn.piao888.user.vo.resq.LoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * 这个类的目标是要代替  org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint
 *
 * @Author： hongzhi.xu
 * @Date: 2023/5/26 7:52 下午
 * @Version 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @PostMapping("/login")
    public ObjectResponse<CurrentUserVo> login(@RequestBody LoginBody loginBody) {

        final CurrentUserVo login = loginService.login(loginBody);
        return ObjectResponse.success(login);
    }

    @GetMapping("/getToken")
    public void getToken(String token) {
        loginService.getToken(token);
    }

    @PostMapping("/getSession")
    public void getSession(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final CurrentUserVo currentUserVo = SessionUtil.get();
        System.out.println(currentUserVo);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object value = session.getAttribute(attributeName);
            // 处理每个属性的值
        }
    }

    private RedirectResolver redirectResolver = new DefaultRedirectResolver();
    private OAuth2RequestValidator oauth2RequestValidator = new DefaultOAuth2RequestValidator();
    private UserApprovalHandler userApprovalHandler = new DefaultUserApprovalHandler();

    @RequestMapping({"/authorize"})
    public ObjectResponse authorize(OauthGrantReq authorizationRequest,Principal principal) {
        //查看相应类型是否包含token或code
        if (!authorizationRequest.getResponse_type().equals("code")) {
            throw new UnsupportedResponseTypeException("Unsupported response types: " + authorizationRequest.getResponse_type());
        } else if (authorizationRequest.getClient_id() == null) {
            throw new InvalidClientException("A client id must be provided");
        } else {

            //获取登陆信息
            if (principal instanceof Authentication && ((Authentication) principal).isAuthenticated()) {
                //从登陆信息中获取客户端信息
                ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClient_id());
                //从登陆信息中获取重定向信息
                String redirectUriParameter = authorizationRequest.getRedirect_uri();
                //从登陆信息中获取 获取跳转url ，然后和目标url进行比对，如果成功 就返回 ，不成功则 抛出异常
                String resolvedRedirect = this.redirectResolver.resolveRedirect(redirectUriParameter, client);
                //判断重定向url是否存在
                if (!StringUtils.hasText(resolvedRedirect)) {
                    throw new RedirectMismatchException("A redirectUri must be either supplied or preconfigured in the ClientDetails");
                } else {
                    //构造请求 设置重定向地址
                    authorizationRequest.setRedirect_uri(resolvedRedirect);
                    //验证这个clientid是否有这个权限
//                        this.oauth2RequestValidator.validateScope(authorizationRequest, client);
//                        authorizationRequest = this.userApprovalHandler.checkForPreApproval(authorizationRequest, (Authentication) principal);
                    //获取请求参数  检查参数是否同意授权
//                        boolean approved = this.userApprovalHandler.isApproved(authorizationRequest, (Authentication) principal);
//                        authorizationRequest.setApproved(approved);
                    //如果同意授权
//                        if (authorizationRequest.isApproved()) {
//                            if (responseTypes.contains("token")) {
//                                return ObjectResponse.success(new LoginResp("错"));
//                            }

                    if (authorizationRequest.getResponse_type().equals("code")) {
                        return ObjectResponse.success(new LoginResp(resolvedRedirect));
                    }
                }
                return ObjectResponse.success("127.0.0.1:9999/home");


            }
        }
        return null;
    }


}
