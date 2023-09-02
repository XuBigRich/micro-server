package cn.piao888.user.service.impl;

import cn.piao888.common.vo.CurrentUserVo;
//import cn.piao888.user.security.UserInfo;
import cn.piao888.user.service.LoginService;
import cn.piao888.user.vo.req.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/5/29 7:31 下午
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private TokenService tokenService;

    @Override
    public CurrentUserVo login(LoginBody loginBody) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
//        final Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        //后台存储的token
//        UserInfo loginUser = (UserInfo) authenticate.getPrincipal();
//        CurrentUserVo currentUserVo = new CurrentUserVo();
//        currentUserVo.setUserName(loginUser.getUserName());
//        currentUserVo.setId(loginUser.getId());
//        currentUserVo.setNickName(loginUser.getNickName());
////        currentUserVo.setToken(tokenService.(currentUserVo));
//        //传给前端的数据
//        return currentUserVo;
        return null;
    }
}
