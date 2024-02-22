package cn.piao888.common.dubbo;


import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.response.ObjectResponse;

/**
 * @Author: lidong
 * @Description  账户服务接口
 * @Date Created in 2019/9/5 16:37
 */
public interface AccountDubboService {

    /**
     * 从账户扣钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
