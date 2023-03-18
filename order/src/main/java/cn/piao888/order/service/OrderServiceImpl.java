package cn.piao888.order.service;

import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.dto.OrderDTO;
import cn.piao888.common.dubbo.AccountDubboService;
import cn.piao888.common.dubbo.OrderDubboService;
import cn.piao888.common.enums.RspStatusEnum;
import cn.piao888.common.response.ObjectResponse;
import cn.piao888.order.domain.TOrder;
import cn.piao888.order.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author 许鸿志
 * @since 2021/9/30
 */
@Slf4j
public class OrderServiceImpl implements OrderDubboService {

    @Autowired
    private OrderMapper orderDAO;
//    @DubboReference(version = "1.0.0")
    @DubboReference
    private AccountDubboService accountDubboService;


    @Override
    public ObjectResponse<OrderDTO> createOrder(cn.piao888.common.dto.OrderDTO orderDTO) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        //扣减用户账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        ObjectResponse objectResponse = accountDubboService.decreaseAccount(accountDTO);

        //生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        //生成订单
        TOrder tOrder = new TOrder();
        BeanUtils.copyProperties(orderDTO, tOrder);
        tOrder.setCount(orderDTO.getOrderCount());
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());
        try {
            orderDAO.createOrder(tOrder);
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        if (objectResponse.getStatus() != 200) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }
}
