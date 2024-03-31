package cn.piao888.gateway.domain.modle.token;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AccessToken {
    private String token;
    private String refreshToken;
    private Long expiresIn;
}