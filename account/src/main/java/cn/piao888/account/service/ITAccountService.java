package cn.piao888.account.service;

import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.response.ObjectResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * * @author lidong
 * @since 2019-09-04
 */
public interface ITAccountService  {

    /**
     * 扣用户钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
