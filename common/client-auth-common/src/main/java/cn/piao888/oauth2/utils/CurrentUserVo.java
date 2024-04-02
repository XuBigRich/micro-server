package cn.piao888.oauth2.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/3/20 2:31 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserVo {
    private Long id;
    private String nickName;
    private String userName;
    private List<String> authorities;
    private LocalDateTime expireTime;
}
