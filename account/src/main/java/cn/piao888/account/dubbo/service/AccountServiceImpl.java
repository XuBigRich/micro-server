package cn.piao888.account.dubbo.service;

import cn.piao888.account.service.ITAccountService;
import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.dubbo.AccountDubboService;
import cn.piao888.common.response.ObjectResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 许鸿志
 * @since 2021/9/29
 */
@DubboService
public class AccountServiceImpl implements AccountDubboService {
    @Autowired
    private ITAccountService accountService;


    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
       return accountService.decreaseAccount(accountDTO);
    }
}
