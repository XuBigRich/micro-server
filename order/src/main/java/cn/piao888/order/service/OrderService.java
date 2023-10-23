package cn.piao888.order.service;

import cn.piao888.order.domain.TOrder;

import java.util.List;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/10/23 15:51
 * @Version 1.0
 */
public interface OrderService {
    List<TOrder> getOrderList();

}
