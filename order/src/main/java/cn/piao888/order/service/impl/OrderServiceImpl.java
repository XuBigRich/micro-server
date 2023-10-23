package cn.piao888.order.service.impl;

import cn.piao888.common.dto.AccountDTO;
import cn.piao888.common.dto.OrderDTO;
import cn.piao888.common.dubbo.AccountDubboService;
import cn.piao888.common.enums.RspStatusEnum;
import cn.piao888.common.response.ObjectResponse;
import cn.piao888.common.utils.SessionUtil;
import cn.piao888.order.domain.TOrder;
import cn.piao888.order.mapper.OrderMapper;
import cn.piao888.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author 许鸿志
 * @since 2021/9/30
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderDAO;

    public List<TOrder> getOrderList() {
        List<TOrder> orderList = orderDAO.getOrderList(SessionUtil.get().getId());
        return orderList;
    }

}
