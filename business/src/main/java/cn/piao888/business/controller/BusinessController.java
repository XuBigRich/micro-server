package cn.piao888.business.controller;

import cn.piao888.business.service.BusinessService;
import cn.piao888.common.dto.BusinessDTO;
import cn.piao888.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author: lidong
 * @Description Dubbo业务执行入口
 * @Date Created in 2019/1/14 17:15
 */
@RestController
@RequestMapping("/business/dubbo")
@Slf4j
public class BusinessController {


    @Autowired
    private BusinessService businessService;

    @GetMapping("/loginSuccessful")
    public void loginSuccessful(Map para) {
        final Set set = para.entrySet();
        final Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            System.out.println(next);
        }
    }

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    /**
     * 模拟用户购买商品下单业务异常逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy2")
    ObjectResponse handleBusiness2(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness2(businessDTO);
    }
}
