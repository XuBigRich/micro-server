package cn.piao888.user.controller;

import cn.piao888.common.response.ObjectResponse;
import cn.piao888.common.utils.SessionUtil;
import cn.piao888.common.vo.CurrentUserVo;
import cn.piao888.user.service.LoginService;
import cn.piao888.user.vo.req.LoginBody;
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

//    @RequestMapping({"/oauth/authorize"})
//    public ModelAndView authorize(@RequestParam AuthorizationRequest authorizationRequest, SessionStatus sessionStatus, Principal principal) {
//        Set<String> responseTypes = authorizationRequest.getResponseTypes();
//        if (!responseTypes.contains("token") && !responseTypes.contains("code")) {
//            throw new UnsupportedResponseTypeException("Unsupported response types: " + responseTypes);
//        } else if (authorizationRequest.getClientId() == null) {
//            throw new InvalidClientException("A client id must be provided");
//        } else {
//            try {
//                if (principal instanceof Authentication && ((Authentication) principal).isAuthenticated()) {
//                    ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
//                    String redirectUriParameter = (String) authorizationRequest.getRequestParameters().get("redirect_uri");
//                    String resolvedRedirect = this.redirectResolver.resolveRedirect(redirectUriParameter, client);
//                    if (!StringUtils.hasText(resolvedRedirect)) {
//                        throw new RedirectMismatchException("A redirectUri must be either supplied or preconfigured in the ClientDetails");
//                    } else {
//                        authorizationRequest.setRedirectUri(resolvedRedirect);
//                        this.oauth2RequestValidator.validateScope(authorizationRequest, client);
//                        authorizationRequest = this.userApprovalHandler.checkForPreApproval(authorizationRequest, (Authentication) principal);
//                        boolean approved = this.userApprovalHandler.isApproved(authorizationRequest, (Authentication) principal);
//                        authorizationRequest.setApproved(approved);
//                        if (authorizationRequest.isApproved()) {
//                            if (responseTypes.contains("token")) {
//                                return this.getImplicitGrantResponse(authorizationRequest);
//                            }
//
//                            if (responseTypes.contains("code")) {
//                                return new ModelAndView(this.getAuthorizationCodeResponse(authorizationRequest, (Authentication) principal));
//                            }
//                        }
//
//                        model.put("authorizationRequest", authorizationRequest);
//                        model.put("org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.ORIGINAL_AUTHORIZATION_REQUEST", this.unmodifiableMap(authorizationRequest));
//                        return this.getUserApprovalPageResponse(model, authorizationRequest, (Authentication) principal);
//                    }
//                } else {
//                    throw new InsufficientAuthenticationException("User must be authenticated with Spring Security before authorization can be completed.");
//                }
//            } catch (RuntimeException var11) {
//                sessionStatus.setComplete();
//                throw var11;
//            }
//        }
//    }


}
