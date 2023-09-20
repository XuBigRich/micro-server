package cn.piao888.user.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

import cn.piao888.common.constant.SecurityConstants;
import cn.piao888.common.vo.CurrentUserVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * token验证处理
 *
 * @author ruoyi
 */
//@Component
public class TokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    public CurrentUserVo getLoginUser() {
        return getLoginUser(((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public CurrentUserVo getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            //这个地方将token转换为Claims信息
//            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
//            String tokenJson = (String) claims.get(Constants.LOGIN_USER_KEY);
            final String tokenJson = parseToken(token);
            //拿到用户存储在redis中的 userKey
            CurrentUserVo user = JSON.parseObject(tokenJson, CurrentUserVo.class);
            //从缓存中取出 存在redis 中的用户信息 ，如果 token过期后 那么将会获得一个null的 LoginUser对象
            return user;
        }
        return null;
    }


    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(CurrentUserVo loginUser) {
        //讲token信息啊放入redis中
        refreshToken(loginUser);
        final String token = JSONObject.toJSONString(loginUser);
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(token);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser 令牌
     * @return 令牌
     */
    public void verifyToken(CurrentUserVo loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        //过期事件减去 当前事件 小于 二十秒
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            //刷新token过期时间
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(CurrentUserVo loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        //延长失效日期
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        //延长redis失效日期，将延长后的loginUser 重新放入redis缓存中
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(String claims) {
        String token = JwtHelper.encode(claims, new MacSigner(secret)).getEncoded();

//        String token = Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public String parseToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
        Jwt jwt = JwtHelper.decodeAndVerify(token, new MacSigner(secret));
        return jwt.getClaims();
    }


    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        return token;
    }
}

