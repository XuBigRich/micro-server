package cn.piao888.order.service;

import cn.piao888.order.dubbo.AccountService;
import cn.piao888.order.dubbo.OrderDTO;
import cn.piao888.order.dubbo.OrderService;
import cn.piao888.order.mapper.OrderMapper;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/**
 * @author 许鸿志
 * @since 2021/9/30
 */
public class OrderServiceImpl extends OrderService {

    private OrderMapper orderDAO;

    private AccountService accountService;

    @Override
    public void create(RpcController controller, OrderDTO request, RpcCallback<OrderDTO> done) {
    }
}
