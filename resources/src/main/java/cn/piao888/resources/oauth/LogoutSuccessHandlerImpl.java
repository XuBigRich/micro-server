package cn.piao888.resources.oauth;

import cn.piao888.common.utils.ServletUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author cmbchina
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
//    @Autowired
//    private TokenStore tokenStore;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        final Object principal = authentication.getPrincipal();
//        tokenStore.
//        User loginUser = tokenStore.removeAccessToken(request.getHeader());
//        if (null != loginUser) {
//            String userName = loginUser.getUsername();
//            // 删除用户缓存记录
//            tokenService.delLoginUser(loginUser.getToken());
//        }
        ServletUtils.renderString(response, JSON.toJSONString("退出成功"));
    }
}
