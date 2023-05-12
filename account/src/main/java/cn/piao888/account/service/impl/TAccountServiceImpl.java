package cn.piao888.account.service.impl;

import cn.piao888.account.mapper.TAccountMapper;
import cn.piao888.account.service.ITAccountService;
import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.enums.RspStatusEnum;
import cn.piao888.common.response.ObjectResponse;
import cn.piao888.common.utils.SessionUtil;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TAccountServiceImpl implements ITAccountService {
    @Autowired
    public TAccountMapper tAccountMapper;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        log.info("开始全局事务，XID = " + RootContext.getXID());

        int account = tAccountMapper.decreaseAccount(SessionUtil.get().getId(), accountDTO.getAmount());
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
