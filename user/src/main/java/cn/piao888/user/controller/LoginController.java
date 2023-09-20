package cn.piao888.user.controller;

import cn.piao888.common.response.ObjectResponse;
import cn.piao888.common.utils.SessionUtil;
import cn.piao888.common.vo.CurrentUserVo;
import cn.piao888.user.service.LoginService;
import cn.piao888.user.vo.req.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Enumeration;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/5/26 7:52 下午
 * @Version 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


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
}
