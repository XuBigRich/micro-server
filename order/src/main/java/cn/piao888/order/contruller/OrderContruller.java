package cn.piao888.order.contruller;

import cn.piao888.common.response.ObjectResponse;
import cn.piao888.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author： hongzhi.xu
 * @Date: 2023/10/23 15:50
 * @Version 1.0
 */
@Controller
public class OrderContruller {
    @Autowired
    public OrderService orderService;

    @GetMapping("/getOrderList")
    public ObjectResponse getOrderList() {
        return ObjectResponse.success(orderService.getOrderList());
    }
}
