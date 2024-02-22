package cn.piao888.common.dto;

import lombok.Data;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/3/20 3:46 下午
 * @Version 1.0
 */
@Data
public class UserDTO {
    private Long id;
    private String nickName;
    private String userName;
    private String password;
}
