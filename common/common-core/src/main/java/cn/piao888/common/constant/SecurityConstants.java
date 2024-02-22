package cn.piao888.common.constant;

/**
 * Security 权限常量
 *
 * @author dmp
 */
public interface SecurityConstants {

    /**
     * 用户名称
     */
    String USER_NAME_HEADER = "x-username-header";

    /**
     * 用户id信息头
     */
    String USER_ID_HEADER = "x-userid-header";

    String TOKEN_PREFIX = "";
    /**
     * 当前登录用户nickName
     */
    String USER_NICK_NAME="x-nick-name";

    /**
     * 请求时间
     */
    String REQUEST_DATE_HEADER = "x-requestdate-header";

    String USER_INFO = "user-info";
}
