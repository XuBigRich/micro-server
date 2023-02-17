package cn.piao888.account.service.impl;

import cn.piao888.account.mapper.TAccountMapper;
import cn.piao888.account.service.ITAccountService;
import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.enums.RspStatusEnum;
import cn.piao888.common.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 * <p>
 * * @author lidong
 *
 * @since 2019-09-04
 */
@Service
public class TAccountServiceImpl implements ITAccountService {
    @Autowired
    public TAccountMapper tAccountMapper;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        int account = tAccountMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (account > 0) {
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        } else {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
        }
        return response;
    }
}
