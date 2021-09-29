package cn.piao888.account.service;

import cn.piao888.account.dubbo.AccountService;
import cn.piao888.account.dubbo.PayDTO;
import cn.piao888.account.dubbo.empty;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/**
 * @author 许鸿志
 * @since 2021/9/29
 */
public class AccountServiceImpl extends AccountService {

    @Override
    public void debit(RpcController controller, PayDTO request, RpcCallback<empty> done) {

    }
}
