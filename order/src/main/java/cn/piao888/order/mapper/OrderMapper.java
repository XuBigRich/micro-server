package cn.piao888.order.mapper;

import cn.piao888.order.domain.TOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 许鸿志
 * @since 2021/9/30
 */
@Mapper
public interface OrderMapper {
    void createOrder(TOrder tOrder);

    List<TOrder> getOrderList(Long userId);
}
