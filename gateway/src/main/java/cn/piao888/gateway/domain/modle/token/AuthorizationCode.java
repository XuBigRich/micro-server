package cn.piao888.gateway.domain.modle.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthorizationCode {
    private String code;
}