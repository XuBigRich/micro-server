package cn.piao888.oauth2.filter;
import cn.piao888.oauth2.constant.SecurityConstants;
import cn.piao888.oauth2.utils.CurrentUserVo;
import cn.piao888.oauth2.utils.SessionUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Enumeration;

/**
 * 租户过滤器
 * 网管调用第一个服务 使用的过滤器
 *
 * @author dmp
 * @date 2019/9/15
 */
@ConditionalOnClass(Filter.class)
@Slf4j
public class TenantFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            //所有header
            JSONObject hearJson = new JSONObject();
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                hearJson.put(key, request.getHeader(key));
            }
            //log.info("请求地址:{},请求头header：{}", request.getRequestURI(), hearJson.toJSONString());
            //当前登录人信息
            Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CurrentUserVo userVo =  new CurrentUserVo(Long.valueOf(principal.getClaim("sub"))
                    , principal.getClaim("nickname")
                    , principal.getClaim("username")
                    , principal.getClaim("authorities")
                    , LocalDateTime.ofInstant(principal.getClaim("exp"), ZoneId.of("+8")));
            SessionUtil.set(userVo);
            filterChain.doFilter(request, response);
        } finally {
            SessionUtil.remove();
        }
    }
}
