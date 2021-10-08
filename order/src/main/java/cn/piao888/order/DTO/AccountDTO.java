package cn.piao888.order.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @Author: heshouyou
 * @Description 账户信息
 * @Date Created in 2019/1/13 16:39
 */
@Data
public class AccountDTO implements Serializable {

    private Integer id;

    private String userId;

    private BigDecimal amount;
}

