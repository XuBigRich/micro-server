package cn.piao888.common.filter;

import cn.piao888.common.constant.SecurityConstants;
import cn.piao888.common.utils.SessionUtil;
import cn.piao888.common.vo.CurrentUserVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
            String userName = request.getHeader(SecurityConstants.USER_NAME_HEADER);
            String nickName = request.getHeader(SecurityConstants.USER_NICK_NAME);
            String requestDate = request.getHeader(SecurityConstants.REQUEST_DATE_HEADER);

            CurrentUserVo userVo = new CurrentUserVo();
            userVo.setId(Long.valueOf(userId));
            userVo.setUserName(userName);
            userVo.setNickName(nickName);
            userVo.setRequestDate(requestDate);
            SessionUtil.set(userVo);
            filterChain.doFilter(request, response);
        } finally {
            SessionUtil.remove();
        }
    }
}
