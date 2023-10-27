package cn.piao888.resources.interceptor;

import cn.piao888.resources.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中令牌
        String token = request.getHeader("token");
        try {
            JWTUtils.verifyToken(token);//验证令牌
            return true;//放行请求
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}