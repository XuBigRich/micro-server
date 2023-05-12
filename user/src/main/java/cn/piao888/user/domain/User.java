package cn.piao888.user.domain;

import lombok.Data;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/3/20 3:50 下午
 * @Version 1.0
 */
@Data
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
}
