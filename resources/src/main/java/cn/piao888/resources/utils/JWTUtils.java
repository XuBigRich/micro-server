package cn.piao888.resources.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author dugt1998@163.com
 * @date 2020/11/8 12:40
 */
public class JWTUtils {
    
    //签名 自己项目中的签名
    private static final String SIGN = "token!@19weawe2r24@#$@%!wewa98du";
 
    /**
     * 生成token
     *
     * @param claim 传入的payload
     * @return
     */
    public static String getToken(Map<String, String> claim) {
        JWTCreator.Builder builder = JWT.create();
        /* 
         * 1、header 用默认的就可
         * 2、设置payload .withClaim
         * 3、设置签名 .sign
         */
        claim.forEach((key, value) -> {
            builder.withClaim(key, value); //设置payload
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 7);
        builder.withExpiresAt(instance.getTime()); //设置token过期时间
        return builder.sign(Algorithm.HMAC256(SIGN));
    }
 
    /**
     *  校验令牌
     *
     * @param token
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}