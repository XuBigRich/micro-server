package cn.piao888.user.service;

import cn.piao888.common.vo.CurrentUserVo;
import cn.piao888.user.vo.req.LoginBody;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/5/29 7:25 下午
 * @Version 1.0
 */
public interface LoginService {
    CurrentUserVo login(LoginBody loginBody);
    void getToken(String token);
}
